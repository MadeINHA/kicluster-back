package org.example.madeinha.domain.kickboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.domain.kickboard.dto.response.KickboardResponse;
import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.example.madeinha.domain.kickboard.service.KickboardService;
import org.example.madeinha.domain.kickboard.service.RedisKickboardService;
import org.example.madeinha.global.result.ResultResponse;
import org.example.madeinha.global.result.code.KickBoardResultCode;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.madeinha.domain.kickboard.dto.response.KickboardResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis/kickboards")
public class RedisKickBoardController {

    private final RedisKickboardService redisKickboardService;
    private final KickboardConverter kickboardConverter;
    private final KickboardService kickboardService;

    @GetMapping("/info/{kickboardId}")
    public ResultResponse<KickboardDetailInfo> getDetailInfo(@PathVariable("kickboardId") Long kickboardId) {
        RedisKickboard kickboard = redisKickboardService.findKickboard(kickboardId);
        KickboardDetailInfo kickboardDetailInfo = kickboardConverter.toKickboardDetailInfo(kickboard);

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_DETAIL_INFO, kickboardDetailInfo);
    }


    @GetMapping("/location/{kickboardId}")
    public ResultResponse<LocationInfo> getLocationInfo(@PathVariable("kickboardId") Long kickboardId) {
        Coordinate coordinate = redisKickboardService.getLoaction(kickboardId);
        LocationInfo locationInfo = kickboardConverter.toLocationInfo(coordinate);

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_LOCATION_INFO, locationInfo);
    }

    @GetMapping("/{clusterId}")
    public ResultResponse<ClusterInfo> getClusterInfo(@PathVariable("clusterId") Integer clusterId) {
        List<RedisKickboard> kickboardList = redisKickboardService.getKickboardInfoByClusterId(clusterId);
        ClusterInfo redisClusterInfo = kickboardConverter.toRedisClusterInfo(kickboardList, clusterId);

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_LIST_BY_CLUSTER_ID, redisClusterInfo);
    }

    @GetMapping()
    public ResultResponse<AllKickboardLocationInfo> getAllKickboardLocationInfo() {
        List<RedisKickboard> kickboardList = redisKickboardService.getAllKickboardLocationInfo();
        AllKickboardLocationInfo allkickboardLocationInfo = kickboardConverter.toAllkickboardLocationInfo(kickboardList);

        return ResultResponse.of(KickBoardResultCode.ALL_KICKBOARD_LOCATION_INFO, allkickboardLocationInfo);
    }


}
