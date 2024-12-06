package org.example.madeinha.global.gRPC.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public abstract class GrpcDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class dbScanResponse {
        List<dbScanCluster> cluster_list;
        Integer max_cluster;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class dbScanCluster {
        Integer cluster_id;
        List<Long> kickboard_list;
    }
}
