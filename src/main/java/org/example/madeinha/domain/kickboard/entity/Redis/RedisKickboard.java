package org.example.madeinha.domain.kickboard.entity.Redis;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@RedisHash(value = "kickboard")
public class RedisKickboard implements Serializable {
    @Id
    @Indexed
    private Long kickboardId;

    public RedisKickboard(final Long kickboardId) {
        this.kickboardId = kickboardId;
    }
}
