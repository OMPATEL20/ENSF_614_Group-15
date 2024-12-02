package com.acmeplex.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acmeplex.model.Payment;
import com.acmeplex.model.Seat;
import com.acmeplex.service.EmailService;
import com.acmeplex.service.PaymentService;
import com.acmeplex.service.SeatService;
import com.acmeplex.service.TicketcancelService;

@Controller
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @Autowired
  private EmailService emailService;

  @Autowired
  private SeatService seatService;

  @Autowired
  private TicketcancelService ticketcancelService;

 
  @GetMapping("/PurchaseForm")
  public String getPurchaseForm(
      @RequestParam String theater,
      @RequestParam String movie,
      @RequestParam String showTime,
      @RequestParam String selectedSeats,
      @RequestParam double totalPrice,
      @RequestParam("theaterText") String theaterText,
      @RequestParam("movieText") String movieText,
      Model model) {

    model.addAttribute("theater", theater);
    model.addAttribute("movie", movie);
    model.addAttribute("showTime", showTime);
    model.addAttribute("selectedSeats", selectedSeats);
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("theaterText", theaterText);
    model.addAttribute("movieText", movieText);
    return "PurchaseForm";
  }

  @PostMapping("/processPayment")
  public String processPayment(
      @RequestParam String email,
      @RequestParam String theater,
      @RequestParam String movie,
      @RequestParam String showTime,
      @RequestParam String totalPrice,
      @RequestParam String selectedSeats,
      // Receives selected seats
      // @RequestParam String theaterText,
      // @RequestParam String movieText,
      Model model) {

    System.out.println("Selected Seats: " + selectedSeats);

    List<Seat> seats = seatService.transformSelectedSeats(selectedSeats);

    for (Seat seat : seats) {
      System.out.println("Processing Seat: Row " + seat.getRow() + ", Number " + seat.getNumber());
      Seat seat_data = new Seat(seat.getRow(), seat.getNumber(), false, email);
      System.out.println("seat_data:" + seat_data);
      seatService.addSeat(seat_data);
    }

    boolean paymentSuccess = true; // Simulating successful payment
    String paymentStatus = paymentSuccess ? "Success" : "Failed";

    // Mock payment saving (you can integrate with your PaymentService)
    Payment payment = new Payment();
    payment.setEmail(email);
    payment.setTheater(theater);
    payment.setMovie(movie);
    payment.setShowTime(showTime);
    payment.setTotalPrice(new BigDecimal(totalPrice));
    payment.setPaymentStatus(paymentStatus);
    payment.setSelectedSeats(selectedSeats);
    payment.setCreatedAt(LocalDateTime.now());

    paymentService.savePayment(payment);

    // Add a success message
    if (paymentSuccess) {

      emailService.sendTicketDetails(email, theater, movie, showTime, totalPrice, selectedSeats);

      model.addAttribute("successMessage", "Payment completed successfully! Reservation details sent to your email.");
      return "ConfirmationPage"; // Return confirmation page
    } else {
      model.addAttribute("errorMessage", "Payment failed. Please try again.");
      return "PurchaseForm"; // Redirect back to the payment form
    }

  }

  @PostMapping("/complete-payment")
  public String completePayment(@RequestParam String creditCard,
      @RequestParam String expiryDate,
      @RequestParam String cvc,
      @RequestParam String email,
      @RequestParam String cardholderName,
      @RequestParam double grandTotal,
      Model model) {
    // Example logic to process payment
    System.out.println("Processing payment with grand total: $" + grandTotal);

    // model.addAttribute("message", "Payment completed successfully!");
    return "SuccessPage"; // Redirect to a success page
  }

}
