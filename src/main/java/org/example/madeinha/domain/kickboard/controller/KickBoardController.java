package org.example.madeinha.domain.kickboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.domain.kickboard.dto.request.KickboardRequest;
import org.example.madeinha.domain.kickboard.dto.response.KickboardResponse;
import org.example.madeinha.domain.kickboard.entity.RDB.Kickboard;
import org.example.madeinha.domain.kickboard.service.KickboardService;
import org.example.madeinha.domain.kickboard.service.RedisKickboardService;
import org.example.madeinha.global.result.ResultResponse;
import org.example.madeinha.global.result.code.KickBoardResultCode;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.madeinha.domain.kickboard.dto.request.KickboardRequest.*;
import static org.example.madeinha.domain.kickboard.dto.response.KickboardResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rdb/kickboards")
public class KickBoardController {

    private final KickboardService kickboardService;
    private final KickboardConverter kickboardConverter;
    private final RedisKickboardService redisKickboardService;

    @PostMapping("/register")
    public ResultResponse<List<KickboardInfo>> register(@RequestBody List<RegisterRequest> requestList) {
        List<Kickboard> kickboardList = requestList.stream()
                .map(request -> kickboardConverter.toEntity(
                        new Coordinate(request.getLongitude(), request.getLatitude()))
                ).toList();

        kickboardService.registerKickboardList(kickboardList);

        List<KickboardInfo> registerInfoList = kickboardList.stream()
                .map(kickboardConverter::toKickboardInfo).toList();

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_REGISTER, registerInfoList);
    }

    @GetMapping("/RDBtoREDIS")
    public ResultResponse<Integer> rdbToRedis() {
        redisKickboardService.register();
        return ResultResponse.of(KickBoardResultCode.KICKBOARD_REGISTER, 1);
    }

    @GetMapping("/location/{kickboardId}")
    public ResultResponse<LocationInfo> getLocation(@PathVariable("kickboardId") Long kickboardId) {
        Coordinate coordinate = kickboardService.location(kickboardId);
        LocationInfo locationInfo = kickboardConverter.toLocationInfo(coordinate);

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_LOCATION_INFO, locationInfo);
    }

    @GetMapping("/{clusterId}")
    public ResultResponse<ClusterInfo> getClusterKickboards(@PathVariable("clusterId") Integer clusterId) {
        List<Kickboard> kickboardList = kickboardService.getKickboardsByClusterId(clusterId);
        ClusterInfo clusterInfo = kickboardConverter.toClusterInfo(kickboardList, clusterId);

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_LIST_BY_CLUSTER_ID, clusterInfo);
    }
}
