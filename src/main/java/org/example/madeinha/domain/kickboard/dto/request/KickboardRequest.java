package org.example.madeinha.domain.kickboard.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public abstract class KickboardRequest {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest {

        @NotNull
        private Double latitude; //위도

        @NotNull
        private Double longitude; //경도



    }
}
