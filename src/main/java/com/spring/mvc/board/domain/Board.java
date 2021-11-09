package com.spring.mvc.board.domain;

import com.spring.mvc.board.dto.ModBoard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Board extends ModBoard {

    private Long boardNo; //글번 호
    private String writer; //작성자
    private String title; //글제목
    private String content; //글내용

    private static long seq;  // Long 하면 안됨 기본값이 null이기 때문에 (주의)

    public Board() {
        this.boardNo = ++seq;
    }

    public Board(String writer, String title, String content) {
        this();
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
