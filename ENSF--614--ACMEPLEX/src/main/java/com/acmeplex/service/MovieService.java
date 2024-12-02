package com.acmeplex.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acmeplex.model.Movie;
import com.acmeplex.repository.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void addMovie(Movie movie) {
        movieRepository.insertMovie(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public Movie getMovieById(int movieId) {
        return movieRepository.getMovieById(movieId);
    }

    public void updateMovie(Movie movie) {
        movieRepository.updateMovie(movie);
    }

    public void deleteMovie(int movieId) {
        movieRepository.deleteMovie(movieId);
    }
}

