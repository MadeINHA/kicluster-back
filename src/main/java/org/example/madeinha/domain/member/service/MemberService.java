package org.example.madeinha.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.member.converter.MemberConverter;
import org.example.madeinha.domain.member.dto.request.MemberRequest;
import org.example.madeinha.domain.member.dto.response.MemberResponse;
import org.example.madeinha.domain.member.entity.Member;
import org.example.madeinha.domain.member.repository.MemberRepository;
import org.example.madeinha.global.error.BusinessException;
import org.example.madeinha.global.error.code.MemberErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.example.madeinha.domain.member.dto.request.MemberRequest.*;
import static org.example.madeinha.domain.member.dto.response.MemberResponse.*;
import static org.example.madeinha.global.error.code.MemberErrorCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    @Transactional
    public Member join(JoinRequest request) {
        Member member = memberConverter.toEntity(request.getNickname());
        memberRepository.save(member);
        return member;
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_BY_MEMBER_ID));
    }

}
