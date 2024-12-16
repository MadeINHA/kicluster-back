package org.example.madeinha.domain.kickboard.service;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.Location.Location;
import org.example.madeinha.domain.Location.LocationRepository;
import org.example.madeinha.domain.fixedArea.entity.AreaType;
import org.example.madeinha.domain.fixedArea.service.FixedAreaService;
import org.example.madeinha.domain.history.dto.HistoryRequest;
import org.example.madeinha.domain.history.service.HistoryService;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.domain.kickboard.dto.request.KickboardRequest;
import org.example.madeinha.domain.kickboard.dto.response.KickboardResponse;
import org.example.madeinha.domain.kickboard.entity.RDB.Kickboard;
import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.example.madeinha.domain.kickboard.repository.RDB.KickboardRepository;
import org.example.madeinha.domain.kickboard.repository.Redis.RedisKickboardRepository;
import org.example.madeinha.domain.notification.service.SSEService;
import org.example.madeinha.global.error.BusinessException;
import org.example.madeinha.global.error.code.KickboardErrorCode;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RedisKickboardService {

    private final RedisKickboardRepository redisKickboardRepository;
    private final KickboardRepository kickboardRepository;
    private final FixedAreaService fixedAreaService;
    private final SSEService sseService;
    private final HistoryService historyService;
    private final KickboardConverter kickboardConverter;
    private final LocationRepository locationRepository;

    public void register() {
        List<Kickboard> kickboardList = kickboardRepository.findAll();
        redisKickboardRepository.deleteAll();
        List<RedisKickboard> redisKickboardList = kickboardList.stream().map(kickboard ->
                new RedisKickboard(
                        kickboard.getKickboardId(), //id
                        kickboard.getLocation().getY(), //latitude
                        kickboard.getLocation().getX(), //longitude
                        kickboard.getClusterId(),
                        kickboard.getParkingZone(),
                        kickboard.getActing()
                )
        ).toList();
        redisKickboardRepository.saveAll(redisKickboardList);
    }

    public RedisKickboard findKickboardById(Long kickboardId) {
        return redisKickboardRepository.findByKickboardId(kickboardId).orElseThrow(
                () -> new BusinessException(KickboardErrorCode.KICKBOARD_NOT_FOUND_BY_ID)
        );
    }

    public Coordinate getLoaction(Long kickboardId) {
        RedisKickboard kickboard = findKickboardById(kickboardId);
        return new Coordinate(kickboard.getLongitude(), kickboard.getLatitude());
    }

    public List<RedisKickboard> getKickboardInfoByClusterId(Integer clusterId) {
        return redisKickboardRepository.findAllByClusterId(clusterId);
    }

    public List<RedisKickboard> getAllKickboardInfo() {
        Iterable<RedisKickboard> kickboards = redisKickboardRepository.findAll();

        return StreamSupport.stream(kickboards.spliterator(), false)
                .filter(kickboard -> !kickboard.getActing()) // Acting == false
                .toList();
    }

    public RedisKickboard towModeLent(HistoryRequest.TowLent request) {
        Long kickboardId = request.getKickboard_id();
        RedisKickboard kickboard = findKickboardById(kickboardId);
        if (kickboard.getParkingZone() != 0) { //금지 구역에 있어야만 가능
            throw new BusinessException(KickboardErrorCode.IS_NOT_TOW_KICKBOARD);
        }
        if (kickboard.getActing()) { // 사용중이면 불가능
            throw new BusinessException(KickboardErrorCode.ALREADY_USING_KICKBOARD);
        }

        kickboard.setActing(true);
        kickboard.setClusterId(-1);
        redisKickboardRepository.save(kickboard);

        historyService.towLent(request); // 사용 기록 엔티티 생성

        return kickboard;
    }

    public KickboardResponse.ReturnInfo towModeReturn(KickboardRequest.ReturnRequest request) {
        Long id = request.getId();
        Double lat = request.getLat();
        Double lng = request.getLng();

        AreaType areaType = checkAreaType(lat, lng);// 현재 위치가 어떤 구역인지 확인
        RedisKickboard kickboard = returnKickboard(request, areaType);

        historyService.towReturn(id); //킥보드 사용 기록을 지우면서 사용종료

        return kickboardConverter.toReturnInfo(id);
    }

    public Boolean towModeReturnCheck(KickboardRequest.ReturnRequest request) {
        Long id = request.getId();

        if (!historyService.isTowKickboard(id)) { //견인 모드로 빌린 킥보드인지 확인
            throw new BusinessException(KickboardErrorCode.IS_NOT_TOW_MODE);
        }

        return (historyService.towReturnCheck(request) == 1); // 지정된 구역에 주차했는지 검증 -> false면 아닌곳에 주차한 것
    }

    public RedisKickboard moveReturn(KickboardRequest.ReturnRequest request) {
        Double lat = request.getLat();
        Double lng = request.getLng();

        AreaType areaType = checkAreaType(lat, lng);//parkingzone 체크

        return returnKickboard(request, areaType);
    }

    public Boolean moveReturnCheck(KickboardRequest.ReturnRequest request) {
        AreaType areaType = checkAreaType(request.getLat(), request.getLng());
        if (areaType.equals(AreaType.PROHIBIT) || areaType.equals(AreaType.ROAD)) {
            return false;
        } else {
            return true;
        }
    }

    public RedisKickboard returnKickboard(KickboardRequest.ReturnRequest request, AreaType areaType) {
        RedisKickboard kickboard = findKickboardById(request.getId());

        kickboard.setLongitude(request.getLng());
        kickboard.setLatitude(request.getLat());
        kickboard.setClusterId(-1);
        kickboard.setActing(false);
        if (areaType.equals(AreaType.EXIST)) {
            kickboard.setParkingZone(1);
        } else if (areaType.equals(AreaType.PROHIBIT) || areaType.equals(AreaType.ROAD)) {
            sseService.broadcastNotification("불량 주차된 킥보드가 감지되었습니다.");
            kickboard.setParkingZone(0);
        } else {
            kickboard.setParkingZone(3);
        }

        return redisKickboardRepository.save(kickboard);
    }

    public AreaType checkAreaType(Double lat, Double lng) {
        return fixedAreaService.getAreaTypeByCoordinate(lat, lng);
    }

    public List<Long> randomMove() {
        Random random = new Random();
        Set<Long> randomSet = new HashSet<>();
        // 중복되지 않는 숫자 뽑기
        while (randomSet.size() < 21) {
            Long randomNumber = 1 + (long) (random.nextDouble() * (355));
            randomSet.add(randomNumber);
        }
        ArrayList<Long> longs = new ArrayList<>(randomSet);

        List<Location> all = locationRepository.findAll();
        Collections.shuffle(all);
        int temp = 0;
        for (Long l : longs) {
            RedisKickboard kickboard = findKickboardById(l);
            kickboard.setLatitude(all.get(temp).getLat());
            kickboard.setLongitude(all.get(temp).getLng());
            redisKickboardRepository.save(kickboard);
            temp++;
        }
        return longs;
    }
}