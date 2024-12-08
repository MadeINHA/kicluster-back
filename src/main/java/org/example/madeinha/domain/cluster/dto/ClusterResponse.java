package org.example.madeinha.domain.cluster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.madeinha.domain.cluster.entity.RedisCluster;
import org.example.madeinha.global.Json.JsonDTO;

import java.util.List;

public abstract class ClusterResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class drawCluster {
        List<RedisCluster> cluster_list;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClusterEntity {
        Integer clusterId;
        List<JsonDTO.JsonKickboardInfo> kickboard_list;
    }
}
