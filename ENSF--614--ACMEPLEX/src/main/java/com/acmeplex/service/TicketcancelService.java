package com.acmeplex.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acmeplex.model.Payment;
import com.acmeplex.model.Seat;
import com.acmeplex.repository.PaymentRepository;
import com.acmeplex.repository.SeatRepository;

@Service
public class TicketcancelService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SeatRepository seatRepository;
    // private final PaymentRepository paymentRepository;

    // public TicketService(SeatRepository seatRepository, PaymentRepository paymentRepository) {
    //     this.seatRepository = seatRepository;
    //     this.paymentRepository = paymentRepository;
    // }

    public Map<String, Object> getUserTickets(String email) {
        Map<String, Object> userTickets = new HashMap<>();

        // Fetch seats by email
        List<Seat> seats = seatRepository.findSeatsByEmail(email);

        // Fetch payment details by email
        List<Payment> payments = paymentRepository.findPaymentsByEmail(email);

        // Combine results
        userTickets.put("seats", seats);
        userTickets.put("payments", payments);

        return userTickets;
    }

    // @Autowired
    // private PaymentRepository paymentRepository; // Inject repository

    // public String cancelTicket(String theater, String movie, String showTime, String selectedSeats) {
    //     Optional<Payment> payment = paymentRepository.findByTheaterAndMovieAndShowTimeAndSelectedSeats(theater, movie, showTime, selectedSeats);

    //     if (payment.isPresent()) {
    //         Payment ticket = payment.get();
    //         if ("Cancelled".equals(ticket.getPaymentStatus())) {
    //             return "Ticket is already cancelled.";
    //         }
    //         ticket.setPaymentStatus("Cancelled");
    //         paymentRepository.save(ticket);
    //         return "Ticket cancelled successfully.";
    //     }
    //     return "No matching ticket found.";
    // }
}
