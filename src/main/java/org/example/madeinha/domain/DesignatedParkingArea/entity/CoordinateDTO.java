package org.example.madeinha.domain.DesignatedParkingArea.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class CoordinateDTO implements Serializable {

    private Double latitude;
    private Double longitude;

    public CoordinateDTO(Double longitude, Double latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
