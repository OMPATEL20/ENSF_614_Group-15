package com.acmeplex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendTicketDetails(String recipient, String theater, String movie, String showTime, String totalPrice, String selectedSeats) {
        String subject = "Your Movie Reservation Details";

//        // Prepare the Thymeleaf context
//        Context context = new Context();
//        context.setVariable("theater", theater);
//        context.setVariable("movie", movie);
//        context.setVariable("showTime", showTime);
//        context.setVariable("totalPrice", totalPrice);
//
//        // Render the HTML template
//        String emailContent = templateEngine.process("reservation-email.html", context);
        
        Context context = new Context();
        context.setVariable("theater", theater);
        context.setVariable("movie", movie);
        context.setVariable("showTime", showTime);
        context.setVariable("totalPrice", totalPrice);
        context.setVariable("selectedSeats", selectedSeats); // Add selectedSeats


        String emailContent = templateEngine.process("reservation-email.html", context);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(emailContent, true); // true indicates HTML content
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

	
}
