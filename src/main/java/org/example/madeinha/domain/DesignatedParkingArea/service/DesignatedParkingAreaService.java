package org.example.madeinha.domain.DesignatedParkingArea.service;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.DesignatedParkingArea.converter.DesignatedParkingAreaConverter;
import org.example.madeinha.domain.DesignatedParkingArea.dto.request.DesignatedParkingAreaRequest;
import org.example.madeinha.domain.DesignatedParkingArea.dto.response.DesignatedParkingAreaResponse;
import org.example.madeinha.domain.DesignatedParkingArea.entity.CoordinateDTO;
import org.example.madeinha.domain.DesignatedParkingArea.entity.DesignatedParkingArea;
import org.example.madeinha.domain.DesignatedParkingArea.repository.DesignatedParkingAreaRepository;
import org.example.madeinha.global.error.BusinessException;
import org.example.madeinha.global.error.code.DesignatedParkingAreaErrorCode;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.madeinha.domain.DesignatedParkingArea.dto.response.DesignatedParkingAreaResponse.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DesignatedParkingAreaService {

    private final DesignatedParkingAreaRepository designatedParkingAreaRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();
    private final DesignatedParkingAreaConverter designatedParkingAreaConverter;

    @Transactional
    public DesignatedParkingArea registerArea(DesignatedParkingAreaRequest.Register request) {
        List<CoordinateDTO> coordinateList = request.getCoordinateList();
        Coordinate[] coordinates = (Coordinate[]) coordinateList.stream()
                .map(coordinate -> new Coordinate(coordinate.getLongitude(),coordinate.getLatitude()))
                .toArray(Coordinate[]::new);

        LinearRing shell = geometryFactory.createLinearRing(coordinates);
        Polygon polygon = geometryFactory.createPolygon(shell);
        DesignatedParkingArea area = designatedParkingAreaConverter.toEntity(request.getName(), polygon);
        return designatedParkingAreaRepository.save(area);
    }

    public DesignatedParkingArea getParkingZoneById(Long parkingZoneId) {
        return designatedParkingAreaRepository.findByParkingAreaId(parkingZoneId).orElseThrow(
                () -> new BusinessException(DesignatedParkingAreaErrorCode.PARKING_ZONE_NOT_FOUND_BY_ID)
        );
    }
}
