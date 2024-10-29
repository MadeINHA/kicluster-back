package org.example.madeinha.global.result.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum GlobalResultCode implements ResultCode {
    ;
    private final int status;
    private final String code;
    private final String message;
}
