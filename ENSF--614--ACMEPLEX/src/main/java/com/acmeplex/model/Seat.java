package com.acmeplex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    @Column(name = "rowNum", nullable = false)
    private int rowNum;

    @Column(name = "seatNum", nullable = false)
    private int seatNum;

    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;

    // @Column(name = "theater_id", nullable = false)
    // private int theater_id;

    @Column(name = "user_email", nullable = true)
    private String user_email;

    // @Column(name = "movie_id", nullable = false)
    // private int movie_id;

    public Seat() {
    }

    public Seat(int row, int number, boolean isAvailable) {
        this.rowNum = row;
        this.seatNum = number;
        this.isAvailable = isAvailable;
    };

    public Seat(int row, int number, boolean isAvailable, String user_email) {
        this.rowNum = row;
        this.seatNum = number;
        this.isAvailable = isAvailable;
        this.user_email = user_email;
    }

    // Getters and Setters
    public int getId() {
        return seatId;
    }

    public int getRow() {
        return rowNum;
    }

    public void setRow(int row) {
        this.rowNum = row;
    }

    public int getNumber() {
        return seatNum;
    }

    public void setNumber(int number) {
        this.seatNum = number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // public int getTheater_id() {
    // return theater_id;
    // }

    // public void setTheater_id(int number) {
    // this.theater_id = number;
    // }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String email) {
        this.user_email = email;
    }

    // public int getMovie_id() {
    // return movie_id;
    // }

    // public void setMovie_id(int number) {
    // this.movie_id = number;
    // }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", rowNum=" + rowNum +
                ", seatNum=" + seatNum +
                ", isAvailable=" + isAvailable +
                ", user_email='" + user_email + '\'' +
                '}';
    }
}
