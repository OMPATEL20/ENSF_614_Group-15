package com.acmeplex.controller;


import com.acmeplex.model.User;
import com.acmeplex.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Maps to src/main/resources/templates/login.html
    }

    // Handle login form submission
    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        User user = userService.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // Store user in session
            session.setAttribute("loggedInUser", user);
            System.out.println("user login done");
            return "redirect:/home-page"; // Redirect to dashboard if login is successful
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Return to login page with error
        }
    }

    // Logout user
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear the session
        return "redirect:/login"; // Redirect to login page
    }
}
