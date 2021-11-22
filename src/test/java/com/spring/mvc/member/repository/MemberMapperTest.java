package com.spring.mvc.member.repository;

import com.spring.mvc.member.domain.Auth;
import com.spring.mvc.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("비밀번호가 인코딩된 상태로 회원가입에 성공해야 한다.")
    void regTest() {
        //원본 비밀번호
        String rawPassword = "def1234!";

        //비밀번호를 암호화인코딩하는 객체 생성
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //암호화된 비밀번호
        String encodePassword = encoder.encode(rawPassword);

        Member member = Member.builder()
                .email("def111@gmail.com")
                .name("박영희")
                .auth(Auth.COMMON)
                .account("def1234")
                .password(encodePassword)
                .build();

        memberMapper.register(member);
    }

    @Test
    @DisplayName("로그인 검증을 정확하게 수행해야 한다.")
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

            //암호화된 비번을 평문으로 디코딩 후 비교
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
    void duplicateTest() {

        String inputId = "def1234";
        int result = memberMapper.isDuplicate(inputId);
        assertEquals(1, result);

        String inputEmail = "def111@gmail.com";
        int result2 = memberMapper.isDuplicate2(inputEmail);
        assertTrue(result2 == 1);
    }
}