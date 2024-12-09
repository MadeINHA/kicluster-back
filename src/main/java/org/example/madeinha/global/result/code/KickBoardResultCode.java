package org.example.madeinha.global.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum KickBoardResultCode implements ResultCode {

    KICKBOARD_REGISTER(200, "SK000", "모든 킥보드 등록을 성공하였습니다."),
    KICKBOARD_LOCATION_INFO(200, "SK001", "킥보드의 위치 조회에 성공하였습니다."),
    KICKBOARD_LIST_BY_CLUSTER_ID(200, "SK002", "클러스터id를 가진 모든 킥보드 조회에 성공하였습니다."),
    KICKBOARD_DETAIL_INFO(200, "SK003", "킥보드 상세 정보 조회를 성공하였습니다."),
    ALL_KICKBOARD_LOCATION_INFO(200, "SK004", "모든 킥보드의 위치 조회에 성공하였습니다."),
    ALL_KCIKBOARD_INFO(200, "SK005", "모든 킥보드 상세 정보 조회에 성공하였습니다."),
    LENT_KICKBOARD_TO_TOW_MODE(200, "SK006", "견인모드를 위한 킥보드 대여에 성공하였습니다."),
    RETURN_KICKBOARD(200, "SK007", "견인 모드 킥보드 반납이 완료되었습니다."),
    ;
    private final int status;
    private final String code;
    private final String message;

}
