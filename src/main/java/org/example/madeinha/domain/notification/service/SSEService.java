package org.example.madeinha.domain.notification.service;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.notification.repository.SSERepository;
import org.example.madeinha.global.error.BusinessException;
import org.example.madeinha.global.error.code.NotificationErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SSEService {

    private static final Long TIMEOUT = 60L * 1000 * 60;
    private final SSERepository sseRepository;

    public SseEmitter subcribe(Long id) {
        SseEmitter sseEmitter = new SseEmitter(TIMEOUT);
        sseRepository.save(id, sseEmitter);
        try {
            sseEmitter.send(SseEmitter.event().name("connect"));
        } catch (IOException e) {
            throw new BusinessException(NotificationErrorCode.NOT_SEND_SSE);
        }

        sseEmitter.onCompletion(() -> sseRepository.deleteById(id));
        sseEmitter.onTimeout(() -> sseRepository.deleteById(id));
        sseEmitter.onError(e -> sseRepository.deleteById(id));

        return sseEmitter;
    }

    public void broadcastNotification(String message) {
        for (Long id : sseRepository.getAllKey()) {
            SseEmitter emitter = sseRepository.findById(id);
            try {
                emitter.send(SseEmitter.event()
                        .name("message")
                        .data(message));
            } catch (IOException e) {
                sseRepository.deleteById(id);
                throw new BusinessException(NotificationErrorCode.NOT_SEND_SSE);
            }
        }
    }
}
