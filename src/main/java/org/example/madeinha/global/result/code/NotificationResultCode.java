package org.example.madeinha.global.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum NotificationResultCode implements ResultCode {

    MESSAGE_SEND(200,"FCM00","푸쉬 알람 메세지를 성공적으로 전송하였습니다.")

    ;

    private final int status;
    private final String code;
    private final String message;

}
