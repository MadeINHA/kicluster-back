package org.example.madeinha.domain.fixedArea.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class CoordinateDTO implements Serializable {

    private Double lat;
    private Double lng;

    public CoordinateDTO(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
