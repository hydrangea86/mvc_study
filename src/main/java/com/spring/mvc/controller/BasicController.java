package com.spring.mvc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//역할: 브라우저의 요청을 처리 (비즈니스 역할 완료 후 화면 연결)
@Controller
@Log4j2  //로그를 만들어주는 기능 (lombok에 있는 기능)
public class BasicController {

    //요청 처리 메서드
    //주소창에 url에 입력 시 오직 get요청 (주소창 집적 입력 post요청은 불가능)
//    @RequestMapping(value = "/req/hello", method = RequestMethod.GET) //3버전
    @GetMapping("/req/hello")
    public String hello() {
        //비즈니스 로직
        System.out.println("안녕안녕 hello hello");

        //화면 연결 - 2가지 방법
        /*
            1. redirect - 재요청
            2. forward - 단순한 파일열기
         */

        //1.
        //return "redirect:/s-login-form";

        //2. forward시에는 열어야 할 jsp의 경로를 문자열로 입력
        return "test";

    }

    // 사용자의 요청 URI: req/ex
    // 응답시에 views폴더 아래에 req_ex폴더 아래이 있는 v1.jsp를 열어야 함.
    @GetMapping("/req/ex")
    public String ex() {
        return "req_ex/v1";
    }

    @GetMapping ("/req/v1")
    public String v1() {
        log.info("/req/v1 GET! ");
        return "req_ex/v1";
    }

    @PostMapping("/req/v1")
    public String v1Post() {
        log.info("/req/v1 POST! ");
        return "req_ex/v1";
    }
}
