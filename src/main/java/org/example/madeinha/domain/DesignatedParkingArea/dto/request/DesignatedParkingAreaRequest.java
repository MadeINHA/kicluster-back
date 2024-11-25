package org.example.madeinha.domain.DesignatedParkingArea.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.madeinha.domain.DesignatedParkingArea.entity.CoordinateDTO;

import java.util.List;

public abstract class DesignatedParkingAreaRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Register {

        @NotNull
        String name;

        @Size(min = 3)
        List<CoordinateDTO> coordinateList;
    }
}
