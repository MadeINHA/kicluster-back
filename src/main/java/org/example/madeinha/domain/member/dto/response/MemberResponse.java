package org.example.madeinha.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public abstract class MemberResponse {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class JoinInfo {
        private Long id;
        private String nickname;
        private LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class MemberInfo{
        private String nickname;
        private Long point;
    }

}
