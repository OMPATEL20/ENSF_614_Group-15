package com.acmeplex.model;

import java.time.LocalDate;

public class Movie {
    private int movieId;
    private String title;
    private double rating;
    private Theater theater; // Reference to the associated Theater
    private LocalDate releaseDate;

    // Constructors
    public Movie() {
    }

    public Movie(int movieId, String title, double rating, Theater theater, LocalDate releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.rating = rating;
        this.theater = theater;
        this.releaseDate = releaseDate;
    }

    // Getters and Setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", theater=" + theater +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
