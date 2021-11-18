package com.spring.mvc.basic.v4_api;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.service.BoardService;
import com.spring.mvc.common.paging.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApiController {

    private final BoardService boardService;

    @GetMapping("/api/hello")
    @ResponseBody //클라이언트측으로 순수한 데이터를 리턴
    public String hello() {
        return "안녕!!!";
    }

    @GetMapping("/api/list")
    @ResponseBody
    public List<String> list() {
        return Arrays.asList("짜장면", "볶음밥", "짬뽕", "탕수육");
    }

    @GetMapping("/api/arr")
    @ResponseBody
    public String[] arr() {
        return new String[] {"멍멍이", "야옹이","짹짹이"};
    }

    @GetMapping("/api/b-list")
    @ResponseBody
    public  List<Board> bList() {
        return boardService.getList(new Page());
    }

}
