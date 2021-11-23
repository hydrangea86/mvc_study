package com.spring.mvc.member.controller;

import com.spring.mvc.member.domain.LoginFlag;
import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    public boolean check(String checkId) {
        log.info("/check 비동기 요청 GET! " + checkId);
        return memberService.isDuplicate(checkId);
    }

    //이메일 중복확인 비동기 통신요청
    @GetMapping("/check2")
    @ResponseBody
    public boolean check2(String checkEmail) {
        log.info("/check2 비동기 요청 GET! " + checkEmail);
        return memberService.isDuplicate2(checkEmail);
    }

    //회원가입 요청 처리
    @PostMapping("/member/sign-up")
    public String signUp(Member member) {
        log.info("/member/sign-up POST! - " + member);
        //서비스 위임
        memberService.signUp(member);
        return "redirect:/";
    }

    //로그인 양식 요청
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    //로그인 검증
    @PostMapping("/loginCheck")
    public String loginCheck(String account, String password, Model model, HttpSession session) {
        log.info("/loginCheck POST ! ID:" + account + ", PW: " + password);
        memberService.login(account, password);
        LoginFlag flag = memberService.login(account, password);
        log.info(flag);
        model.addAttribute("msg", flag);

        //http 프로토몰 무상태성, 비연결성 두번째 redirect시 client 정보가 리셋 됨

        //로그인 성공시 열거형은 문자열이 아님
        if (flag == LoginFlag.SUCCESS) {
            session.setAttribute("loginUser", memberService.getMember(account));
            //model.addAttribute("loginuser", memberService.getMember(account));
            return "redirect:/";
        }
        return "member/login";
    }

    //로그아웃
    @GetMapping("/sign-out")
    public String logout(HttpSession session) {
        Member member = (Member) session.getAttribute("loginUser");
        if (member != null) {
            session.removeAttribute("loginUser");
            session.invalidate(); //세션 무효화
        }
        return "redirect:/";
    }
}
