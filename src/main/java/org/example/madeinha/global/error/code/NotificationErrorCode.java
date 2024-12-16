package org.example.madeinha.global.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum NotificationErrorCode implements ErrorCode {
    NOT_SEND_SSE(404, "NE000", "SSE 등록이 되지 않았습니다."),

    ;
    private final int status;
    private final String code;
    private final String message;
}
