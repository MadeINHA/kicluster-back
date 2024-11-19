package org.example.madeinha.domain.notification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public abstract class FcmResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class FcmSend {
        private String targetToken;
        private String title;
        private String body;
    }

}
