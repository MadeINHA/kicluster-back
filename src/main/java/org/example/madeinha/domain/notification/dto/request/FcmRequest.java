package org.example.madeinha.domain.notification.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public abstract class FcmRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class FcmRequestDTO {

        @NotBlank(message = "토큰을 입력해야 합니다.")
        private String targetToken;
        private String title;
        private String body;

    }
}
/*
* 프론트에서 디바이스 토큰, title, body를 넘겨 주는 것
* or
* rdb에 회원 - 토큰 쌍을 저장
 */