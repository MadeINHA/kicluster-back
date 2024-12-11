package org.example.madeinha.domain.kickboard.service;

import lombok.RequiredArgsConstructor;
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
import org.example.madeinha.global.error.BusinessException;
import org.example.madeinha.global.error.code.KickboardErrorCode;
import org.example.madeinha.global.redis.RedisService;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RedisKickboardService {

    private final RedisKickboardRepository redisKickboardRepository;
    private final KickboardRepository kickboardRepository;
    private final FixedAreaService fixedAreaService;
    private final RedisService redisService;
    private final HistoryService historyService;
    private final KickboardConverter kickboardConverter;

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

//        redisService.updateActing(kickboardId, true); //acting을 True로 변경
        historyService.towLent(request); // 사용 기록 엔티티 생성

        return kickboard;
    }

    public KickboardResponse.TowModeReturnInfo towModeReturn(KickboardRequest.ReturnRequest request) {
        Long id = request.getId();
        Double lat = request.getLat();
        Double lng = request.getLng();

        if (!historyService.isTowKickboard(id)) {
            throw new BusinessException(KickboardErrorCode.IS_NOT_TOW_MODE);
        }

        Boolean check = (historyService.towReturnCheck(request) == 1); // 지정된 구역에 주차했는지 검증 -> false면 아닌곳에 주차한 것

        RedisKickboard kickboard = findKickboardById(id);

        AreaType areaType = checkAreaType(lat, lng);// 현재 위치가 어떤 구역인지 확인
        if (areaType.equals(AreaType.EXIST)) {
            kickboard.setParkingZone(1);
        } else if (areaType.equals(AreaType.PROHIBIT) || areaType.equals(AreaType.ROAD)) {
            kickboard.setParkingZone(0);
        } else {
            kickboard.setParkingZone(3);
        }

        kickboard.setLatitude(lat);
        kickboard.setLongitude(lng);
        kickboard.setActing(false);

        redisKickboardRepository.save(kickboard);

        historyService.towReturn(id); //킥보드 사용 기록을 지우면서 사용종료

        return kickboardConverter.toTowModeReturnInfo(id, check);
    }

    public RedisKickboard moveKickboard(KickboardRequest.ReturnRequest request) {
        Double lat = request.getLat();
        Double lng = request.getLng();

        RedisKickboard kickboard = findKickboardById(request.getId());

        AreaType areaType = checkAreaType(lat, lng);//parkingzone 체크
        if (areaType.equals(AreaType.EXIST)) {
            kickboard.setParkingZone(1);
        } else if (areaType.equals(AreaType.PROHIBIT) || areaType.equals(AreaType.ROAD)) {
            kickboard.setParkingZone(0);
        } else {
            kickboard.setParkingZone(3);
        }

        kickboard.setLatitude(lat);
        kickboard.setLongitude(lng);

        return redisKickboardRepository.save(kickboard);
    }

    public AreaType checkAreaType(Double lat, Double lng) {
        return fixedAreaService.getAreaTypeByCoordinate(lat, lng);
    }
}