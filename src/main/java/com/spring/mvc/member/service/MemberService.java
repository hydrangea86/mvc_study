package com.spring.mvc.member.service;

import com.spring.mvc.member.domain.LoginFlag;
import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//****************
import static com.spring.mvc.member.domain.LoginFlag.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    //아이디 중복확인 중간처리
    public boolean isDuplicate(String checkId) {
        return memberMapper.isDuplicate(checkId) == 1;
    }
    //이메일 중복확인 중간처리
    public boolean isDuplicate2(String checkEmail) {
        return memberMapper.isDuplicate2(checkEmail) == 1;
    }

    public void signUp(Member member) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPw = encoder.encode(member.getPassword());
        member.setPassword(encodedPw);

        memberMapper.register(member);
    }

    //로그인 중간처리
    public LoginFlag login(String account, String password) {
        Member member = memberMapper.getUser(account);
        if (member != null) {
         String dbPw = member.getPassword(); //db password
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(password, dbPw) ? SUCCESS : NO_PW; // 3항식
        } else {
          return NO_ID;
        }
    }

    //회원정보 조회하기
    public Member getMember (String account) {
        return memberMapper.getUser(account);
    }


}
