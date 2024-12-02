package com.acmeplex.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.acmeplex.model.Theater;

@Repository
public class TheaterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Theater> getAllTheaters() {
        String sql = "select * from theater";
        return jdbcTemplate.query(sql, new RowMapper<Theater>() {
            @Override
            public Theater mapRow(ResultSet rs, int rowNum) throws SQLException {
                Theater theater = new Theater();
                theater.setId(rs.getInt("theaterId"));
                theater.setName(rs.getString("theaterName"));
                return theater;
            }
        });
    }


    // Theater findByTheaterName(String theaterName);
    @SuppressWarnings("deprecation")
    public Theater getTheaterById(String theater2) {
        String sql = "SELECT * FROM theater WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{theater2}, (rs, rowNum) -> {
            Theater theater = new Theater();
            theater.setId(rs.getInt("id"));
            theater.setName(rs.getString("name"));
            return theater;
        });
    }

    @SuppressWarnings("deprecation")
    public Theater findByTheaterName(String name) {
        String sql = "SELECT * FROM theater WHERE theaterName = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) -> {
            Theater theater = new Theater();
            theater.setId(rs.getInt("id"));
            theater.setName(rs.getString("name"));
            return theater;
        });
    }





   

    
 
}
