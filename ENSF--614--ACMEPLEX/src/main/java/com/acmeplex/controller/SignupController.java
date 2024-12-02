package com.acmeplex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acmeplex.model.User;
import com.acmeplex.repository.UserRepository;
import com.acmeplex.service.UserService;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

//    @PostMapping("/signup")
//    public String processSignup(@ModelAttribute User user) {
//        userService.saveUser(user);
//        System.out.println("Card Number: " + user.getCardNum());
//        // Simulate saving the user to the database (add your database logic here)
//        System.out.println("User signed up: " + user);
//        return "redirect:/login";
//    }
//    
    
    @PostMapping("/signup")
    public String processSignup(@ModelAttribute User user, @RequestParam("cvv") String cvv) {
        // Hash the CVV and set it on the user
        user.setCvv(cvv);
        userService.saveUser(user);
        
        if (user.getCardNum() == null || user.getCardNum().isEmpty()) {
            throw new IllegalArgumentException("Card number is required");
        }
        // Save the user to the database
        userRepository.save(user);
        System.out.println("User signed up: " + user);
        return "redirect:/login";
    }
     

}