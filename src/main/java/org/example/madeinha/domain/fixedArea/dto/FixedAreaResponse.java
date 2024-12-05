package org.example.madeinha.domain.fixedArea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.madeinha.domain.fixedArea.entity.AreaType;
import org.example.madeinha.domain.fixedArea.entity.CoordinateDTO;

import java.util.List;

public abstract class FixedAreaResponse {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterList{
        List<RegisterInfo> registerInfoList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterInfo {
        Long areaId;
        String name;
        AreaType areaType;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AreaInfo{
        String name;
        List<CoordinateDTO> path;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AreaInfoByType{
        AreaType areaType;
        List<AreaInfo> areaInfoList;
    }

}
