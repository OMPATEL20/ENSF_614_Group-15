package com.acmeplex.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.acmeplex.model.Seat;
import com.acmeplex.repository.SeatRepository;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    // Add a new seat
    public void addSeat(Seat seat) {
        seatRepository.insertSeat(seat);
    }

    // Get all seats
    public List<Seat> getAllSeats() {
        return seatRepository.getAllSeats();
    }

    // Get a seat by ID
    public Seat getSeatById(int seatId) {
        return seatRepository.getSeatById(seatId);
    }

    // Update a seat
    public void updateSeat(Seat seat) {
        seatRepository.updateSeat(seat);
    }

    // Delete a seat
    public void deleteSeat(String userEmail, int rowNum, int seatNum) {
        seatRepository.deleteSeat(userEmail, rowNum, seatNum);
    }

    public Map<Integer, List<Seat>> generateSeatMap(String user_email) {
        Map<Integer, List<Seat>> seatMap = new HashMap<>();

        for (int row = 1; row <= 8; row++) {
            List<Seat> seats = new ArrayList<>();
            for (int number = 1; number <= 10; number++) {
                // Check if the seat exists in the database
                boolean isAvailable = !seatRepository.isSeatExists(row, number);
                seats.add(new Seat(row, number, isAvailable, user_email));
            }
            seatMap.put(row, seats);
        }

        return seatMap;
    }

    public Map<Integer, List<Seat>> generateSeatMap() {
        System.out.println("here");
    // public Map<Integer, List<Seat>> generateSeatMap(int theater_id, int movie_id) {
        Map<Integer, List<Seat>> seatMap = new HashMap<>();

        for (int row = 1; row <= 8; row++) {
            List<Seat> seats = new ArrayList<>();
            for (int number = 1; number <= 10; number++) {
                // Check if the seat exists in the database
                boolean isAvailable = !seatRepository.isSeatExists(row, number);
                seats.add(new Seat(row, number, isAvailable));
                // seats.add(new Seat(row, number, isAvailable, theater_id, movie_id));
            }
            seatMap.put(row, seats);
        }

        return seatMap;
    }

    public List<Seat> transformSelectedSeats(String selectedSeats) {
        List<Seat> seats = new ArrayList<>();

        // Split the string by comma to get individual seat positions
        String[] seatArray = selectedSeats.split(",");

        for (String seatInfo : seatArray) {
            // Split each seat by '-' to extract row and seat number
            String[] parts = seatInfo.split("-");

            // Extract row and seat number
            int row = Integer.parseInt(parts[0].substring(1)); // Remove 'R' and parse the number
            int number = Integer.parseInt(parts[1].substring(1)); // Remove 'S' and parse the number

            // Create a Seat object and add it to the list
            seats.add(new Seat(row, number, true));
        }

        return seats;
    }
}


//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.acmeplex.model.Seat;
//import com.acmeplex.repository.SeatRepository;
//
//@Service
//public class SeatService {
//
//    @Autowired
//    private SeatRepository seatRepository;
//
//    public List<Seat> getSeatsByShowtime(Long showtimeId) {
//        return seatRepository.findByShowtimeId(showtimeId);
//    }
//
//    public void updateSeatAvailability(List<Integer> seatIds, boolean isAvailable) {
//        Object seat = ((Object) seatRepository.findAll(seatIds));
//        ((Seat) seat).setAvailable(isAvailable);
////        seatRepository.saveAll seat);
//    }
//}
