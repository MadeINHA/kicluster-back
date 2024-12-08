package org.example.madeinha.domain.cluster.converter;

import org.example.madeinha.domain.cluster.entity.RedisCluster;
import org.springframework.stereotype.Component;

import static org.example.madeinha.domain.cluster.dto.ClusterResponse.ClusterEntity;

@Component
public class ClusterConverter {

    public ClusterEntity toClusterEntity(RedisCluster redisCluster) {
        return ClusterEntity.builder()
                .clusterId(redisCluster.getCluster_id())
                .kickboard_list(redisCluster.getKickboard_list())
                .build();
    }
}
