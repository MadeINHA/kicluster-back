package org.example.madeinha.domain.kickboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public abstract class KickboardResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KickboardInfo {
        Long kickboardId;
        Double latitude;
        Double longitude;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AllKickboardLocationInfo {
        List<LocationInfo> path;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KickboardDetailInfo{
        Long kickboardId;
        Double latitude;
        Double longitude;
        Integer clusterId;
        Integer parkingZone;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationInfo {
        Double latitude;
        Double longitude;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClusterInfo { // 특정 클러스터에 속하는 킥보드 리스트 반환
        Integer clusterId;
        List<KickboardInfo> kickboardInfoList;
    }






}
