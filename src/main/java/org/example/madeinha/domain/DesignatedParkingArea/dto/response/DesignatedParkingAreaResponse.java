package org.example.madeinha.domain.DesignatedParkingArea.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.madeinha.domain.DesignatedParkingArea.entity.CoordinateDTO;

import java.util.List;

public abstract class DesignatedParkingAreaResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterInfo {
        Long parkingAreaId;
        String name;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParkingZoneInfo {
        String name;
        List<CoordinateDTO> coordinateList;
    }

}
