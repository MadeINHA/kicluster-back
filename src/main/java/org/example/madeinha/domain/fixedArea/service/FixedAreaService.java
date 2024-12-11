package org.example.madeinha.domain.fixedArea.service;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.fixedArea.converter.FixedAreaConverter;
import org.example.madeinha.domain.fixedArea.dto.FixedAreaRequest;
import org.example.madeinha.domain.fixedArea.entity.AreaType;
import org.example.madeinha.domain.fixedArea.entity.CoordinateDTO;
import org.example.madeinha.domain.fixedArea.entity.FixedArea;
import org.example.madeinha.domain.fixedArea.repository.FixedAreaRepository;
import org.example.madeinha.global.error.BusinessException;
import org.example.madeinha.global.error.code.FixedAreaErrorCode;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FixedAreaService {

    private final FixedAreaRepository fixedAreaRepository;
    private final FixedAreaConverter fixedAreaConverter;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional
    public List<FixedArea> registerArea(FixedAreaRequest.FixedAreaRegister request, AreaType areaType) {
        List<FixedAreaRequest.Area> areaList = request.getAreaList();
        List<FixedArea> fixedAreaList = new ArrayList<>();
        for (FixedAreaRequest.Area area : areaList) {
            List<CoordinateDTO> path = area.getPath();
            Coordinate[] coordinates = path.stream()
                    .map(
                            coordinate -> new Coordinate(coordinate.getLng(), coordinate.getLat())
                    )
                    .toArray(Coordinate[]::new);
            LinearRing shell = geometryFactory.createLinearRing(coordinates);
            Polygon polygon = geometryFactory.createPolygon(shell);
            FixedArea fixedArea = fixedAreaConverter.toEntity(area.getName(), polygon, areaType);
            fixedAreaList.add(fixedArea);
        }
        return fixedAreaRepository.saveAll(fixedAreaList);
    }

    public FixedArea getFixedAreaById(Long areaId) {
        return fixedAreaRepository.findByAreaId(areaId).orElseThrow(
                () -> new BusinessException(FixedAreaErrorCode.FIXED_AREA_NOT_FOUND_BY_ID)
        );
    }

    public List<FixedArea> getFixedAreaByType(AreaType areaType) {
        return fixedAreaRepository.findAllByAreaType(areaType);
    }

    public FixedArea getNearestExistArea(Double lat, Double lng) {
        return fixedAreaRepository.findNearestExistFixedArea(lat, lng).get();
    }

    public AreaType getAreaTypeByCoordinate(Double lat, Double lng) {
        Optional<FixedArea> areaContainingPoint = fixedAreaRepository.findAreaContainingPoint(lat, lng);
        if (areaContainingPoint.isEmpty()) {
            return AreaType.CLUSTER;
        }
        return areaContainingPoint.get().getAreaType();
    }

}
