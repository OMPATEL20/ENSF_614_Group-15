package com.acmeplex.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.acmeplex.model.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SeatRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isSeatExists(int row, int number) {
        String sql = "SELECT COUNT(*) FROM seats WHERE rowNum = ? AND seatNum = ?";
        @SuppressWarnings("deprecation")
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{row, number}, Integer.class);
        return count != null && count > 0;
    }

    // Insert a seat
    public void insertSeat(Seat seat) {
        String sql = "INSERT INTO seats (rowNum, seatNum, isAvailable, user_email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, seat.getRow(), seat.getNumber(), seat.isAvailable(), seat.getUser_email());
    }

    @SuppressWarnings("deprecation")
    public List<Seat> findSeatsByEmail(String email) {
        String sql = "SELECT * FROM seats WHERE user_email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> mapRowToSeat(rs));
    }

    // Retrieve all seats
    public List<Seat> getAllSeats() {
        String sql = "SELECT * FROM seats";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToSeat(rs));
    }

    // Retrieve a seat by ID
    @SuppressWarnings("deprecation")
    public Seat getSeatById(int seatId) {
        String sql = "SELECT * FROM seats WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{seatId}, (rs, rowNum) -> mapRowToSeat(rs));
    }

    // Update a seat
    public void updateSeat(Seat seat) {
        String sql = "UPDATE seats SET rowNum = ?, seatNum = ?, isAvailable = ? WHERE id = ?";
        jdbcTemplate.update(sql, seat.getRow(), seat.getNumber(), seat.isAvailable(), seat.getId());
    }

    // Delete a seat
    public void deleteSeat(String userEmail, int rowNum, int seatNum) {
        String sql = "DELETE FROM seats WHERE user_email = ? AND rowNum = ? AND seatNum = ?";
        jdbcTemplate.update(sql, userEmail, rowNum, seatNum);
    }

    // Map ResultSet to Seat object
    private Seat mapRowToSeat(ResultSet rs) throws SQLException {
        return new Seat(
            rs.getInt("rowNum"),
            rs.getInt("seatNum"),
            rs.getBoolean("isAvailable"),
            rs.getString("user_email")
        );
    }
}


