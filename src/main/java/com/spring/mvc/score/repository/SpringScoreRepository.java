package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ssr")
@Log4j2
@RequiredArgsConstructor
public class SpringScoreRepository implements ScoreRepository{

    //JDBC의 중복코드를 처리해주는 템플릿 (실행 안될 시 Spring jdbc starter 깔아야함)
    private final JdbcTemplate jdbcTemplate; // @RequiredArgsConstructor 사용 시 생성자 자동 생성 해줌


    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO score " +
                "VALUES (seq_score.nextval, ?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, score.getName(), score.getKor(), score.getEng()
                , score.getMath(), score.getTotal(), score.getAverage()) ==  1;
    }

    @Override
    public List<Score> findAll() {
        String sql = "SELECT * FROM score";
        //return jdbcTemplate.query(sql, (rs, rowNum) -> new Score(rs));
        return jdbcTemplate.query("SELECT * FROM score", (rs, rowNum) -> new Score(rs));
    }

    @Override
    public Score findOne(int stuNum) {
        String sql = "SELECT * FROM score WHERE stu_num=?";
        //리스트 하나 리턴할 때 queryForObject 또는 get(0)
        // return jdbcTemplate.query(sql, (rs, rowNum) -> new Score(rs), stuNum).get(0);
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Score(rs), stuNum);

    }

    @Override
    public boolean remove(int stuNum) {
        String sql = "DELETE FROM score WHERE stu_num=?";
        return jdbcTemplate.update(sql, stuNum) == 1;
    }
}
