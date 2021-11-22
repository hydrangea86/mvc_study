package com.spring.mvc.member.controller;

import com.spring.mvc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
