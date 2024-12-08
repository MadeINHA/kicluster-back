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

    private Double cent_lat;

    private Double cent_lng;

    public RedisCluster(Integer cluster_id, List<JsonDTO.JsonKickboardInfo> kickboard_list, Double cent_lat, Double cent_lng) {
        this.cluster_id = cluster_id;
        this.kickboard_list = kickboard_list;
        this.cent_lat = cent_lat;
        this.cent_lng = cent_lng;
    }
}
