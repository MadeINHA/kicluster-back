package org.example.madeinha.global.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;

public abstract class JsonDTO {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonAllClusterInfo implements Serializable {
        @JsonProperty("cluster_list")
        List<JsonClusterInfo> clusterList;
        @JsonProperty("max_cluster")
        Integer maxCluster;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonClusterInfo implements Serializable{
        @JsonProperty("cluster_id")
        Integer clusterId;
        @JsonProperty("kickboard_list")
        List<JsonKickboardInfo> kickboardList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonKickboardInfo implements Serializable{
        Long id;
        Double lat;
        Double lng;
    }
}
