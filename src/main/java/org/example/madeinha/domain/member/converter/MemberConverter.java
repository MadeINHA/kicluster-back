package org.example.madeinha.domain.member.converter;

import org.example.madeinha.domain.member.entity.Member;
import org.springframework.stereotype.Component;

import static org.example.madeinha.domain.member.dto.response.MemberResponse.JoinInfo;
import static org.example.madeinha.domain.member.dto.response.MemberResponse.MemberInfo;

@Component
public class MemberConverter {

    public JoinInfo toJoinInfo(Member member) {
        return JoinInfo.builder()
                .id(member.getMemberId())
                .nickname(member.getNickname())
                .createdAt(member.getCreateAt())
                .build();
    }

    public MemberInfo toMemberInfo(Member member) {
        return MemberInfo.builder()
                .nickname(member.getNickname())
                .point(member.getPoint())
                .build();
    }

    public Member toEntity(String nickname) {
        return Member.builder()
                .nickname(nickname)
                .point(0l)
                .build();
    }
}
