package org.example.madeinha.domain.history.converter;

import org.example.madeinha.domain.history.entity.History;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class HistoryConverter {

    public History toEntity(Long kickboardId, Polygon zone ) {
        return History.builder()
                .kickboardId(kickboardId)
                .zone(zone)
                .build();
    }
}
