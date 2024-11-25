package org.example.madeinha.global.result.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum DesignatedParkingAreaResultCode implements ResultCode {
    PARKING_ZONE_REGISTER(200, "SP000", "지정된 주차 구역 등록에 성공하였습니다."),
    PARKING_ZONE_INFO_BY_ID(200,"SP001","해당 주차 구역의 정보 조회에 성공하였습니다.")
    ;

    private final int status;
    private final String code;
    private final String message;
}
