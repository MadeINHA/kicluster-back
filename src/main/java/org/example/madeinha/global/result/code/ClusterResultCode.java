package org.example.madeinha.global.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.global.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum ClusterResultCode implements ResultCode {

    ALL_CLUSTER_INFO(200, "SC000", "모든 클러스터 정보 조회에 성공하였습니다."),
    REFRESH_CLUSTER(200, "SC001", "클러스터 정보 새로고침에 성공하였습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
