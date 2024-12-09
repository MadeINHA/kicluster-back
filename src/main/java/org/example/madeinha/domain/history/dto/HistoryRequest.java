package org.example.madeinha.domain.history.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.madeinha.domain.fixedArea.entity.CoordinateDTO;

import java.util.List;

public abstract class HistoryRequest {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TowLent {
        Long kickboard_id;
        List<CoordinateDTO> path;
    }
}
