package org.example.madeinha.domain.DesignatedParkingArea.converter;

import org.example.madeinha.domain.DesignatedParkingArea.dto.response.DesignatedParkingAreaResponse;
import org.example.madeinha.domain.DesignatedParkingArea.entity.CoordinateDTO;
import org.example.madeinha.domain.DesignatedParkingArea.entity.DesignatedParkingArea;
import org.example.madeinha.domain.DesignatedParkingArea.entity.ParkingZone;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.example.madeinha.domain.DesignatedParkingArea.dto.response.DesignatedParkingAreaResponse.*;

@Component
public class DesignatedParkingAreaConverter {

    public DesignatedParkingArea toEntity(String name, Polygon polygon) {
        return DesignatedParkingArea.builder()
                .parkingAreaName(name)
                .zone(polygon)
                .build();
    }

    public RegisterInfo toRegisterInfo(DesignatedParkingArea designatedParkingArea) {
        return RegisterInfo.builder()
                .parkingAreaId(designatedParkingArea.getParkingAreaId())
                .name(designatedParkingArea.getParkingAreaName())
                .build();
    }

    public ParkingZoneInfo toParkingZoneInfo(DesignatedParkingArea designatedParkingArea) {
        Polygon zone = designatedParkingArea.getZone();
        List<CoordinateDTO> list = Arrays.stream(zone.getCoordinates()).map(
                coordinate -> new CoordinateDTO(coordinate.getX(), coordinate.getY())
        ).toList();

        return ParkingZoneInfo.builder()
                .name(designatedParkingArea.getParkingAreaName())
                .coordinateList(list)
                .build();
    }
}
