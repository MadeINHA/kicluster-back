package org.example.madeinha.global.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    MEMBER_NOT_FOUND_BY_MEMBER_ID(404, "EM001", "해당 memberId를 가진 회원이 없습니다."),

    ;

    private final int status;
    private final String code;
    private final String message;
}
