package org.example.madeinha.domain.kickboard.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public abstract class KickboardRequest {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Register {

        @NotNull
        private Double lat; //위도

        @NotNull
        private Double lng; //경도
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest {
        List<Register> registerList;
    }

}
