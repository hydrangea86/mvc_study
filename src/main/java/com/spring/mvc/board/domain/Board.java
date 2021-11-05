package com.spring.mvc.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Board {

    private Long boardNo; //글번 호
    private String writer; //작성자
    private String title; //글제목
    private String content; //글내용

}
