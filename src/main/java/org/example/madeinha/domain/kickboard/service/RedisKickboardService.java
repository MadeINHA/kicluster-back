package org.example.madeinha.domain.kickboard.service;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.domain.kickboard.dto.response.KickboardResponse;
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
    private final KickboardConverter kickboardConverter;

    public void register() {
        List<Kickboard> kickboardList = kickboardRepository.findAll();
        List<RedisKickboard> redisKickboardList = kickboardList.stream().map(kickboard ->
                new RedisKickboard(
                        kickboard.getKickboardId(), //id
                        kickboard.getLocation().getY(), //latitude
                        kickboard.getLocation().getX(), //longitude
                        kickboard.getClusterId(),
                        kickboard.getParkingZone(),
                        kickboard.getBorder()
                )
        ).toList();
        redisKickboardRepository.saveAll(redisKickboardList);
    }

    public RedisKickboard findKickboard(Long kickboardId){
        return redisKickboardRepository.findByKickboardId(kickboardId).orElseThrow(
                () -> new BusinessException(KickboardErrorCode.KICKBOARD_NOT_FOUND_BY_ID)
        );
    }

    public Coordinate getLoaction(Long kickboardId) {
        RedisKickboard kickboard = findKickboard(kickboardId);
        return new Coordinate(kickboard.getLongitude(), kickboard.getLatitude());
    }

    public List<RedisKickboard> getKickboardInfoByClusterId(Integer clusterId) {
        return redisKickboardRepository.findByClusterId(clusterId);
    }

    public List<RedisKickboard> getAllKickboardLocationInfo() {
        Iterable<RedisKickboard> kickboards = redisKickboardRepository.findAll();
        return StreamSupport.stream(kickboards.spliterator(), false).toList();
    }
}
