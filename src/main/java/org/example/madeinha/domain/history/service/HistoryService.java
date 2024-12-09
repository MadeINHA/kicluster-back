package org.example.madeinha.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.fixedArea.entity.CoordinateDTO;
import org.example.madeinha.domain.history.converter.HistoryConverter;
import org.example.madeinha.domain.history.dto.HistoryRequest;
import org.example.madeinha.domain.history.entity.History;
import org.example.madeinha.domain.history.repository.HistoryRepository;
import org.example.madeinha.domain.kickboard.dto.request.KickboardRequest;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryConverter historyConverter;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional
    public History towLent(HistoryRequest.TowLent request) {
        List<CoordinateDTO> path = request.getPath();
        Coordinate[] coordinates = path.stream().map(
                coordinate -> new Coordinate(coordinate.getLng(), coordinate.getLat())
        ).toArray(Coordinate[]::new);
        LinearRing shell = geometryFactory.createLinearRing(coordinates);
        Polygon polygon = geometryFactory.createPolygon(shell);

        History history = historyConverter.toEntity(request.getKickboard_id(), polygon);

        return historyRepository.save(history);
    }

    public Boolean towReturnCheck(KickboardRequest.ReturnRequest request) {
        return historyRepository.checkReturn(request.getId(), request.getLat(), request.getLng());
    }

    @Transactional
    public void towReturn(Long id) {
        History history = historyRepository.findByKickboardId(id).get();
        history.delete();
    }
}
