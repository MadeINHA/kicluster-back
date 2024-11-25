package org.example.madeinha.global.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum KickboardErrorCode implements ErrorCode {

    KICKBOARD_NOT_FOUND_BY_ID(404, "EK000", "해당 id를 가진 킥보드가 존재하지 않습니다."),

    ;
    private final int status;
    private final String code;
    private final String message;
}
