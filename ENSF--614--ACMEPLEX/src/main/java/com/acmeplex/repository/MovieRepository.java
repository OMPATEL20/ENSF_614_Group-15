package com.acmeplex.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.acmeplex.model.Movie;
import com.acmeplex.model.Theater;

@Repository
public class MovieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert a movie
    public void insertMovie(Movie movie) {
        String sql = "INSERT INTO movie (title, releaseDate, theaterId, rating) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, movie.getTitle(), movie.getReleaseDate(),movie.getTheater(), movie.getRating());
    }

    // Retrieve all movies
    public List<Movie> getAllMovies() {
        String sql = "SELECT m.movieId, m.title, m.rating, m.releaseDate, t.theaterId, t.theaterName AS theaterName FROM MOVIE m JOIN THEATER t ON m.theaterId = t.theaterId";
                        // "\"t.theaterId, t.name AS theaterName \" + // Alias 't.name' as 'theaterName'\r\n" +
                        // "\"FROM MOVIE m \" +\r\n" +
                        // "\"JOIN THEATER t ON m.theaterId = t.theaterId";
        // String sql = "SELECT * FROM movie";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToMovie(rs));
    }

    // Retrieve a movie by ID
    @SuppressWarnings("deprecation")
    public Movie getMovieById(int movieId) {
        String sql = "SELECT * FROM movie WHERE movieId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{movieId}, (rs, rowNum) -> mapRowToMovie(rs));
    }

    // Update a movie
    public void updateMovie(Movie movie) {
        String sql = "UPDATE movie SET title = ?, releaseDate = ?, theaterId = ?, rating = ? WHERE movie_id = ?";
        jdbcTemplate.update(sql, movie.getTitle(), movie.getReleaseDate(), movie.getTheater(), movie.getRating(), movie.getMovieId());
    }

    // Delete a movie
    public void deleteMovie(int movieId) {
        String sql = "DELETE FROM movie WHERE movie_id = ?";
        jdbcTemplate.update(sql, movieId);
    }

    // Map ResultSet to Movie object
    private Movie mapRowToMovie(ResultSet rs) throws SQLException {
        Theater theater = new Theater(
            rs.getInt("theaterId"),  // Extract theaterId
            rs.getString("theaterName")    // Extract theater name (ensure it's part of the SELECT query)
        );

        return new Movie(
            rs.getInt("movieId"),
            rs.getString("title"),
            rs.getDouble("rating"),
            theater,
            rs.getDate("releaseDate").toLocalDate()
        );
    }
}
