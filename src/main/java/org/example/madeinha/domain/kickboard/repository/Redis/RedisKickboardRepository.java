package org.example.madeinha.domain.kickboard.repository.Redis;

import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RedisKickboardRepository extends CrudRepository<RedisKickboard,Long> {
    Optional<RedisKickboard> findByKickboardId(Long kickboardId);

    List<RedisKickboard> findByClusterId(Integer clusterId);

}
