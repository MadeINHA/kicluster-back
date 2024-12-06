package org.example.madeinha.domain.kickboard.service;

import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.JsonProcessingException;
import org.example.madeinha.domain.kickboard.dto.request.KickboardRequest;
import org.example.madeinha.global.Json.JsonConverter;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.global.Json.JsonDTO;
import org.example.madeinha.domain.kickboard.entity.RDB.Kickboard;
import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.example.madeinha.domain.kickboard.repository.RDB.KickboardRepository;
import org.example.madeinha.domain.kickboard.repository.Redis.RedisKickboardRepository;
import org.example.madeinha.global.error.BusinessException;
import org.example.madeinha.global.error.code.KickboardErrorCode;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RedisKickboardService {

    private final RedisKickboardRepository redisKickboardRepository;
    private final KickboardRepository kickboardRepository;
    private final JsonConverter jsonConverter;


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

    public RedisKickboard findKickboardByCoordinate(Double lat, Double lng) {
        return redisKickboardRepository.findByLatitudeAndLongitude(lat, lng).orElseThrow(
                () -> new BusinessException(KickboardErrorCode.KICKBOARD_NOT_FOUND_BY_COORDINATE)
        );
    }

    public Coordinate getLoaction(Long kickboardId) {
        RedisKickboard kickboard = findKickboardById(kickboardId);
        return new Coordinate(kickboard.getLongitude(), kickboard.getLatitude());
    }

    public List<RedisKickboard> getKickboardInfoByClusterId(Integer clusterId) {
        return redisKickboardRepository.findByClusterId(clusterId);
    }

    public List<RedisKickboard> getAllKickboardInfo() {
        Iterable<RedisKickboard> kickboards = redisKickboardRepository.findAll();
        return StreamSupport.stream(kickboards.spliterator(), false).toList();
    }

    public RedisKickboard towModeLent(KickboardRequest.Register request) {
        RedisKickboard kickboard = findKickboardByCoordinate(request.getLat(), request.getLng());
        if (kickboard.getParkingZone() != 0) {

        }
        if (kickboard.getActing()) {
            throw new BusinessException(KickboardErrorCode.ALREADY_USING_KICKBOARD);
        }
        kickboard.lentKickboard();
        return redisKickboardRepository.save(kickboard);
    }
}