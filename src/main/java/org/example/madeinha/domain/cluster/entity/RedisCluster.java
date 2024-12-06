package org.example.madeinha.domain.cluster.entity;

import lombok.Getter;
import org.example.madeinha.global.Json.JsonDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@Getter
@RedisHash(value = "cluster")
public class RedisCluster implements Serializable {

    @Id
    @Indexed
    private Integer cluster_id;

    List<JsonDTO.JsonKickboardInfo> kickboard_list;

    public RedisCluster(Integer cluster_id, List<JsonDTO.JsonKickboardInfo> kickboard_list) {
        this.cluster_id = cluster_id;
        this.kickboard_list = kickboard_list;
    }
}
