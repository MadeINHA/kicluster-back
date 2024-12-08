package org.example.madeinha.domain.cluster.repository;

import org.example.madeinha.domain.cluster.entity.RedisCluster;
import org.springframework.data.repository.CrudRepository;

public interface RedisClusterRepository extends CrudRepository<RedisCluster,Integer> {

}
