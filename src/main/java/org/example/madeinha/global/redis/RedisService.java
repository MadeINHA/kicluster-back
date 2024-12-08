package org.example.madeinha.global.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final HashOperations<String,String,Object> hashOperations;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void updateClusterId(Long kickboardId, Integer newClusterId) {
        String key = "kickboard:" + kickboardId;
        hashOperations.put(key, "clusterId", newClusterId);
    }

    public void updateCoordinate(Long kickboradId, Double lat, Double lng) {
        String key = "kickboard:" + kickboradId;
        hashOperations.put(key, "lat", lat);
        hashOperations.put(key, "lng", lng);
    }

    public void updateParkingZone(Long kickboardId, Integer parkingZone) {
        String key = "kickboard:" + kickboardId;
        hashOperations.put(key, "parkingZone", parkingZone);
    }
}
