package org.example.madeinha.domain.kickboard.converter;

import org.example.madeinha.domain.kickboard.entity.RDB.Kickboard;
import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.example.madeinha.domain.kickboard.dto.response.KickboardResponse.*;

@Component
public class KickboardConverter {

    GeometryFactory geometryFactory = new GeometryFactory();

    public Kickboard toEntity(Coordinate coordinate) {
        return Kickboard.builder()
                .location(geometryFactory.createPoint(coordinate))
                .clusterId(-1)
                .parkingZone(-1)
                .border(false)
                .build();
    }

    public KickboardInfo toKickboardInfo(Kickboard kickboard) {
        return KickboardInfo.builder()
                .kickboardId(kickboard.getKickboardId())
                .longitude(kickboard.getLocation().getX())
                .latitude(kickboard.getLocation().getY())
                .build();
    }

    public LocationInfo toLocationInfo(Coordinate coordinate) {
        return LocationInfo.builder()
                .longitude(coordinate.getX())
                .latitude(coordinate.getY())
                .build();
    }

    public ClusterInfo toClusterInfo(List<Kickboard> kickboards, Integer clusterId) {
        List<KickboardInfo> kickboardInfoList = kickboards.stream()
                .map(this::toKickboardInfo).toList();

        return ClusterInfo.builder()
                .clusterId(clusterId)
                .kickboardInfoList(kickboardInfoList)
                .build();
    }
/*
RDB
--------------------------------------------------------------------------------------------------------------------------
REDIS
 */
    public ClusterInfo toRedisClusterInfo(List<RedisKickboard> kickboards, Integer clusterId) {
        List<KickboardInfo> kickboardInfoList = kickboards.stream()
                .map(kickboard ->
                        KickboardInfo.builder()
                                .kickboardId(kickboard.getKickboardId())
                                .longitude(kickboard.getLongitude())
                                .latitude(kickboard.getLatitude())
                                .build()
                ).toList();
        return ClusterInfo.builder()
                .clusterId(clusterId)
                .kickboardInfoList(kickboardInfoList)
                .build();
    }

    public KickboardDetailInfo toKickboardDetailInfo(RedisKickboard redisKickboard) {
        return KickboardDetailInfo.builder()
                .kickboardId(redisKickboard.getKickboardId())
                .longitude(redisKickboard.getLongitude())
                .latitude(redisKickboard.getLatitude())
                .clusterId(redisKickboard.getClusterId())
                .parkingZone(redisKickboard.getParkingZone())
                .build();
    }

    public AllKickboardLocationInfo toAllkickboardLocationInfo(List<RedisKickboard> kickboards) {
        List<LocationInfo> list = kickboards.stream()
                .map(kickboard ->
                        LocationInfo.builder()
                        .longitude(kickboard.getLongitude())
                        .latitude(kickboard.getLatitude())
                        .build()
                ).toList();
        return AllKickboardLocationInfo.builder()
                .path(list)
                .build();
    }
}
