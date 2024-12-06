package org.example.madeinha.domain.cluster.repository;

import org.example.madeinha.domain.cluster.entity.RedisCluster;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RedisClusterRepository extends CrudRepository<RedisCluster,Integer> {

    Optional<RedisCluster> findByCluster_id(Integer id);
}
