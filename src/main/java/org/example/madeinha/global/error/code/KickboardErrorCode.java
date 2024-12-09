package org.example.madeinha.global.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum KickboardErrorCode implements ErrorCode {

    KICKBOARD_NOT_FOUND_BY_ID(404, "EK000", "해당 id를 가진 킥보드가 존재하지 않습니다."),
    KICKBOARD_NOT_FOUND_BY_COORDINATE(404, "EK001", "해당 위치에 존재하는 킥보드가 존재하지 않습니다."),
    ALREADY_USING_KICKBOARD(404, "EK002", "이미 사용중인 킥보드 입니다."),
    IS_NOT_TOW_KICKBOARD(404, "EK003", "견인 대상 킥보드가 아닙니다."),
    ;
    private final int status;
    private final String code;
    private final String message;
}
