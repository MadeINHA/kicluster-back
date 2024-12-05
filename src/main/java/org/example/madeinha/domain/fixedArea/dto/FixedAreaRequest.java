package org.example.madeinha.domain.fixedArea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.madeinha.domain.fixedArea.entity.CoordinateDTO;

import java.util.List;

public abstract class FixedAreaRequest {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Area {
        String name;
        List<CoordinateDTO> path;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FixedAreaRegister {
        List<Area> areaList;
    }

}
