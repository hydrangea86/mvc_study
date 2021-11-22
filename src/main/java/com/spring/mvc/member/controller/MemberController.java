package com.spring.mvc.member.controller;

import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.service.MemberService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    //회원가입 form 요청
    @GetMapping("/join")
    public String join() {
        return "member/join";
    }


    //아이디 중복확인 비동기 통신요청
    @GetMapping("/check")
    @ResponseBody
    public boolean check(String CheckId) {
        log.info("/check 비동기 요청 GET! " + CheckId);

        return memberService.isDuplicate(CheckId);
    }

    //이메일 중복확인 비동기 통신요청
    @GetMapping("/check2")
    @ResponseBody
    public boolean check2(String CheckEmail) {
        log.info("/check 비동기 요청 GET! " + CheckEmail);

        return memberService.isDuplicate(CheckEmail);
    }

    //회원가입 요청 처리 form은 비동기가 아님
    @PostMapping("/member/sign-up")
    public String signUp(Member member) {
        log.info("/member/sign-up POST " + member);

        //서비스 위임
        memberService.signUp(member);
        return "redirect:/";
    }
}
