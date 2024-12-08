package org.example.madeinha.domain.kickboard.converter;

import org.example.madeinha.domain.kickboard.entity.RDB.Kickboard;
import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.example.madeinha.domain.kickboard.dto.response.KickboardResponse.*;

@Component
public class KickboardConverter {

    GeometryFactory geometryFactory = new GeometryFactory();

    public Kickboard toEntity(Coordinate coordinate) {
        return Kickboard.builder()
                .location(geometryFactory.createPoint(coordinate))
                .clusterId(-1)
                .parkingZone(3)
                .acting(false)
                .build();
    }

    public KickboardInfo toKickboardInfo(Kickboard kickboard) {
        return KickboardInfo.builder()
                .kickboardId(kickboard.getKickboardId())
                .lat(kickboard.getLocation().getX())
                .lng(kickboard.getLocation().getY())
                .build();
    }

    public LocationInfo toLocationInfo(Coordinate coordinate) {
        return LocationInfo.builder()
                .lng(coordinate.getX())
                .lat(coordinate.getY())
                .build();
    }

    public ClusterInfo toRedisClusterInfo(List<RedisKickboard> kickboards, Integer clusterId) {
        List<KickboardInfo> kickboardInfoList = kickboards.stream()
                .map(kickboard ->
                        KickboardInfo.builder()
                                .kickboardId(kickboard.getKickboardId())
                                .lng(kickboard.getLongitude())
                                .lat(kickboard.getLatitude())
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
                .lng(redisKickboard.getLongitude())
                .lat(redisKickboard.getLatitude())
                .clusterId(redisKickboard.getClusterId())
                .parkingZone(redisKickboard.getParkingZone())
                .acting(redisKickboard.getActing())
                .build();
    }

    public AllKickboardLocationInfo toAllkickboardLocationInfo(List<RedisKickboard> kickboards) {
        List<LocationInfo> list = kickboards.stream()
                .map(kickboard ->
                        LocationInfo.builder()
                        .lng(kickboard.getLongitude())
                        .lat(kickboard.getLatitude())
                        .build()
                ).toList();
        return AllKickboardLocationInfo.builder()
                .path(list)
                .build();
    }

    public AllKickboardInfo toAllKickboardInfoUseList(List<RedisKickboard> kickboards) {
        List<KickboardDetailInfo> list = kickboards.stream()
                .map(kickboard -> KickboardDetailInfo.builder()
                        .kickboardId(kickboard.getKickboardId())
                        .lat(kickboard.getLatitude())
                        .lng(kickboard.getLongitude())
                        .parkingZone(kickboard.getParkingZone())
                        .clusterId(kickboard.getClusterId())
                        .acting(kickboard.getActing())
                        .build()
                ).toList();

        return AllKickboardInfo.builder()
                .kickboardList(list)
                .build();
    }

    public AllKickboardInfo toAllKickboardInfoUseIter(Iterable<RedisKickboard> kickboards) {
        List<KickboardDetailInfo> list = new ArrayList<>();
        for (RedisKickboard kickboard : kickboards) {
            list.add(toKickboardDetailInfo(kickboard));
        }

        return AllKickboardInfo.builder()
                .kickboardList(list)
                .build();
    }

    public TowModeLentInfo toTowModeLentInfo(RedisKickboard kickboard) {
        return TowModeLentInfo.builder()
                .kickboardId(kickboard.getKickboardId())
                .acting(kickboard.getActing())
                .lentTime(LocalDateTime.now())
                .build();
    }
}
