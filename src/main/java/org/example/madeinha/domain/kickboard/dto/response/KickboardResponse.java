package org.example.madeinha.domain.kickboard.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public abstract class KickboardResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KickboardInfo {
        Long kickboardId;
        Double lat;
        Double lng;
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
    public static class AllKickboardInfo {
        @JsonProperty("kickboard_list")
        List<KickboardDetailInfo> kickboardList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KickboardDetailInfo{
        Long kickboardId;
        Double lat;
        Double lng;
        Integer clusterId;
        Integer parkingZone;
        Boolean acting;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationInfo {
        Double lat;
        Double lng;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClusterInfo { // 특정 클러스터에 속하는 킥보드 리스트 반환
        Integer clusterId;
        List<KickboardInfo> kickboardInfoList;
    }


    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TowModeLentInfo {
        Long kickboardId;
        Boolean acting;
        LocalDateTime lentTime;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TowModeReturnInfo {
        Long kickboardId;
        LocalDateTime returnTime;
        Boolean check;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MoveInfo {
        Long kickboardId;
        Integer parkingZone;
        LocalDateTime updateTime;
    }
}
