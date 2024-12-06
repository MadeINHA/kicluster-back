package org.example.madeinha.domain.kickboard.entity.Redis;


import lombok.AllArgsConstructor;
import lombok.Getter;
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

    @Indexed
    private Double latitude;

    @Indexed
    private Double longitude;

    @Indexed
    private Integer clusterId;

    @Indexed
    private Integer parkingZone;

    @Indexed
    private Boolean acting;

    public void lentKickboard() {
        this.acting = true;
    }

    public void returnKickboard(){
        this.acting = false;
    }
}
