package org.example.madeinha.global.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum MemberResultCode implements ResultCode {

    SIGN_UP(200,"SM000","새로운 회원 등록에 성공하였습니다."),
    MEMBER_INFO(200,"SM001","회원 정보를 성공적으로 조회하였습니다."),

    ;

    private final int status;
    private final String code;
    private final String message;
}
