package com.acmeplex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acmeplex.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTheaterAndMovieAndShowTimeAndSelectedSeats(String theater, String movie, String showTime, String selectedSeats);
    @Query("SELECT p FROM Payment p WHERE p.email = ?1")
    List<Payment> findPaymentsByEmail(String email);
}
