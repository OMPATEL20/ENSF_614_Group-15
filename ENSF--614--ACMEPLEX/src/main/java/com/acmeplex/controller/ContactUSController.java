package com.acmeplex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactUSController {

    @Autowired
    private JavaMailSender mailSender;

 
    

    @GetMapping("/Contact-Us")
    public String showContactUsPage() {
        return "Contact-Us"; // Matches Contact-Us.html in the templates folder
    }
    @PostMapping("/send-email")
    public String sendEmail(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("message") String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("acmeplexmovieapp@gmail.com");
        mailMessage.setTo("acmeplexmovieapp@gmail.com");
        mailMessage.setSubject("New Contact Form Submission");
        mailMessage.setText("Name: " + name + "\nEmail: " + email + "\n\nMessage:\n" + message);

        mailSender.send(mailMessage);

        return "thank-you"; // Redirect to a thank-you page after sending
    }
}
