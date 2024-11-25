package org.example.madeinha.domain.kickboard.entity.Redis;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@RedisHash(value = "kickboard")
public class RedisKickboard implements Serializable {

    @Id
    @Indexed
    private Long kickboardId;

    @Indexed
    private Double latitude;

    @Indexed
    private Double longitude;

    @Indexed
    private Integer clusterId;

    @Indexed
    private Integer parkingZone;

    @Indexed
    private Boolean border;


    public RedisKickboard(
            final Long kickboardId,
            final Double latitude,
            final Double longitude,
            final Integer clusterId,
            final Integer parkingZone,
            final Boolean border
    ) {
        this.kickboardId = kickboardId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.clusterId = clusterId;
        this.parkingZone = parkingZone;
        this.border = border;
    }
}
