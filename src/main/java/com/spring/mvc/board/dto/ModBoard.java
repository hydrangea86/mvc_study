package com.spring.mvc.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ModBoard {

    private Long boardNo;
    private String writer;
    private String title;
    private String content;

}
