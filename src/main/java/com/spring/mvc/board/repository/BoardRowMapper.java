package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardRowMapper implements RowMapper<Board> {
    @Override
    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Board(rs);
    }
}
