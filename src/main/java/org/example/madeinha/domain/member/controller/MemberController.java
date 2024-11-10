package org.example.madeinha.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.member.converter.MemberConverter;
import org.example.madeinha.domain.member.entity.Member;
import org.example.madeinha.domain.member.service.MemberService;
import org.example.madeinha.global.result.ResultResponse;
import org.example.madeinha.global.result.code.MemberResultCode;
import org.springframework.web.bind.annotation.*;

import static org.example.madeinha.domain.member.dto.request.MemberRequest.*;
import static org.example.madeinha.domain.member.dto.response.MemberResponse.*;
import static org.example.madeinha.global.result.code.MemberResultCode.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @PostMapping("/join")
    public ResultResponse<JoinInfo> getJoinInfo(@RequestBody JoinRequest request) {
        Member member = memberService.join(request);
        return ResultResponse.of(SIGN_UP, memberConverter.toJoinInfo(member));
    }

    @GetMapping("/info")
    public ResultResponse<MemberInfo> getMemberInfo(@RequestParam("memberId") Long memberId) {
        Member member = memberService.findMember(memberId);
        return ResultResponse.of(MEMBER_INFO, memberConverter.toMemberInfo(member));
    }
}
