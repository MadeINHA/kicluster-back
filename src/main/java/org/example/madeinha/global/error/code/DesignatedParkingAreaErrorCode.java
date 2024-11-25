package org.example.madeinha.global.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum DesignatedParkingAreaErrorCode implements ErrorCode {
    PARKING_ZONE_NOT_FOUND_BY_ID(404, "EP000", "해당 Id를 가진 지정 주차 구역이 존재하지 않습니다."),

    ;
    private final int status;
    private final String code;
    private final String message;
}
