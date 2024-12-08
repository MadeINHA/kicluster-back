package org.example.madeinha.global.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum FixedAreaResultCode implements ResultCode {
    EXIST_FIXED_AREA_REGISTER(200, "SA000", "기존 주차 구역 등록에 성공하였습니다."),
    ROAD_FIXED_AREA_REGISTER(200, "SA001", "기존 도로 구역 등록에 성공하였습니다."),
    PROHIBIT_FIXED_AREA_REGISTER(200, "SA002", "기존 금지 구역 등록에 성공하였습니다."),
    EXIST_FIXED_AREA_INFO(200, "SA004", "모든 기존 주차 구역 정보 조회에 성공하였습니다."),
    ROAD_FIXED_AREA_INFO(200, "SA005", "모든 도로 구역 정보 조회에 성공하였습니다."),
    PROHIBIT_FIXED_AREA_INFO(200, "SA006", "모든 금지 구역 정보 조회에 성공하였습니다."),
    NEAREST_EXIST_FIXED_AREA_INFO(200, "SA007", "가장 가까운 기존 주차 구역 조회에 성공하였습니다."),
    ;
    private final int status;
    private final String code;
    private final String message;
}
