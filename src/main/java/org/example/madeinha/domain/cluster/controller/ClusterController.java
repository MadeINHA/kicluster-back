package org.example.madeinha.domain.cluster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.cluster.entity.RedisCluster;
import org.example.madeinha.domain.cluster.service.ClusterService;
import org.example.madeinha.global.result.ResultResponse;
import org.example.madeinha.global.result.code.ClusterResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.madeinha.domain.cluster.dto.ClusterResponse.drawCluster;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clusters")
public class ClusterController {

    private final ClusterService clusterService;

    @GetMapping("/draw")
    @Operation(summary = "군집을 그릴 수 있도록 군집 정보를 반환하는 API", description = "db에 저장된 클러스터 정보를 모두 반환하는 api")
    public ResultResponse<drawCluster> drawCluster() {
        List<RedisCluster> allClusterInfo = clusterService.getAllClusterInfo();

        drawCluster result = drawCluster.builder().cluster_list(allClusterInfo).build();

        return ResultResponse.of(ClusterResultCode.ALL_CLUSTER_INFO, result);
    }

    @GetMapping("/refresh")
    @Operation(summary = "유저가 새로고침을 누르면 호출하는 API", description = "dbscan, convexHull 알고리즘을 다시 수행하고 db에 반영하며 클러스터 정보를 모두 반환하는 api")
    public ResultResponse<drawCluster> refreshMap() throws JsonProcessingException {

        clusterService.updateKickboardInfo();

        List<RedisCluster> allClusterInfo = clusterService.getAllClusterInfo();

        drawCluster result = drawCluster.builder().cluster_list(allClusterInfo).build();

        return ResultResponse.of(ClusterResultCode.REFRESH_CLUSTER, result);
    }

}
