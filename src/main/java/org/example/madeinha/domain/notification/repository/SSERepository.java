package org.example.madeinha.domain.notification.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SSERepository {

    private final Map<Long, SseEmitter> emitterMap = new ConcurrentHashMap<>();

    public SseEmitter findById(Long id) {
        return emitterMap.get(id);
    }

    public SseEmitter save(Long id, SseEmitter sseEmitter) {
        return emitterMap.put(id, sseEmitter);
    }

    public SseEmitter deleteById(Long id) {
        return emitterMap.remove(id);
    }

    public Set<Long> getAllKey() {
        return emitterMap.keySet();
    }
}
