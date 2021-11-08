package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class MemoryBoardRepository implements BoardRepository {

    //메모리 저장소
    private Map<Long, Board> boardMap = new HashMap<>();

    public MemoryBoardRepository() {
        boardMap.put(1L, new Board("개그맨", "아라따리또", "아리비안나이트 신비한이야기"));
        boardMap.put(2L, new Board("뽀로로", "노는게", "제일 조와~~~"));
        boardMap.put(3L, new Board("Jack", "hey !!", "hello ^^v"));
        boardMap.put(4L, new Board("Steven", "열정", "이 쎄상에!!"));
        boardMap.put(5L, new Board("말썽쟁이", "ㅋㅋㅋㅋㅋ", "ㅂㄴㅁㅇ러ㅗ냄롱나ㅗㅓㅣ론ㅈ알넝린ㅂㄷㅈㅂㄷㅂㅇㄹㄴㅁㅇㅁㄴ언이라"));
    }

    @Override
    public List<Board> getArticles() {
        List<Board> boardList = new ArrayList<>();
        for (Long boardNo : boardMap.keySet()) {
            Board board = boardMap.get(boardNo);
            boardList.add(board);
        }
        return boardList;
    }

    @Override
    public Board getContent(Long boardNo) {
        Board board = boardMap.get(boardNo);
        log.info("board -" + board);
        return board;
    }

    @Override
    public boolean insert(Board board) {
        boardMap.put(board.getBoardNo(), board);
        return false;
    }

    @Override
    public boolean delete(Long boardNo) {
        return false;
    }

    @Override
    public boolean update(Board board) {
        return false;
    }
}
