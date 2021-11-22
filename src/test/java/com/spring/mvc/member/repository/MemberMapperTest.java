package com.spring.mvc.member.repository;

import com.spring.mvc.member.domain.Auth;
import com.spring.mvc.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @DisplayName("비밀번호가 인코딩된 상태로 회원가입에 성공해야 한다.")
//    @Test
//    //원본 비밀번호
//    String rawPassword = "def1234";
//
//
//    //비밀번호를 암호화인코딩하는 객체 생성
//    BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
//
//    //비밀번호
//
//    void regTest() {
//        Member member = Member.builder()
//                .account("abc1234")
//                .email("abc1111@gamil.com")
//                .name("김철수")
//                .auth(Auth.COMMON)
//                .password("def1234")
//                .build();
//
//
//        memberMapper.register(member);
//    }

    @Test

    void loginTest() {
        //로그인 시도 아이디
        String inputId = "def1234";
        //로그인 시도 비밀번호
        String inputPw = "def1234!";

        //로그인 시도 아이디를 기본으로 회원정보 조회
        Member member = memberMapper.getUser(inputId);

        if (member != null) {
            //디비에 저장된 비번
            String dbPw = member.getPassword();

            //암호화된 비번을 평문으로 디콩 후 비교
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(inputPw, dbPw)) {
                System.out.println("로그인 성공!");
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
            }
        } else {
            System.out.println("회원가입된 아이디가 아닙니다.");
        }

    }

    @Test
    @DisplayName("아이디, 이메일 중복확인에 성공해야 한다.")
    void duplicatedTest() {
        String inputId = "def1234";
        int result = memberMapper.isDuplicate(inputId);
        assertTrue(result == 1); //알트 엔터 쳐서

        String inputEmail = "def111@gmail.com";
        int result2 = memberMapper.isDuplicate(inputEmail);
        assertTrue(result2 == 1);
    }
}