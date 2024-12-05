package org.example.madeinha.global.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum FixedAreaErrorCode implements ErrorCode {
    FIXED_AREA_NOT_FOUND_BY_ID(404, "AP000", "해당 Id를 가진 구역이 존재하지 않습니다."),

    ;
    private final int status;
    private final String code;
    private final String message;
}
