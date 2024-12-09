package org.example.madeinha.domain.kickboard.entity.Redis;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@RedisHash(value = "kickboard")
@AllArgsConstructor
public class RedisKickboard implements Serializable {

    @Id
    @Indexed
    private Long kickboardId;

    private Double latitude;

    private Double longitude;

    @Indexed
    @Setter
    private Integer clusterId;

    @Indexed
    @Setter
    private Integer parkingZone;

    @Indexed
    private Boolean acting;

}
