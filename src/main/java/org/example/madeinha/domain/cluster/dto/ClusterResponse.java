package org.example.madeinha.domain.cluster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.madeinha.domain.cluster.entity.RedisCluster;

import java.util.List;

public abstract class ClusterResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class drawCluster {
        List<RedisCluster> cluster_list;
    }
}
