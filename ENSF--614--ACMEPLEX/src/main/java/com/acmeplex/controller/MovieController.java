package com.acmeplex.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acmeplex.model.Movie;
import com.acmeplex.model.Seat;
import com.acmeplex.model.Theater;
import com.acmeplex.service.MovieService;
import com.acmeplex.service.SeatService;
import com.acmeplex.service.TheaterService;

@Controller
public class MovieController {

	@Autowired
	private TheaterService theaterService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private SeatService seatService;

	/**
	 * Load the home page with a list of theaters.
	 */
	@GetMapping("/")
	public String showindexPage() {
		return "index"; // Maps to src/main/resources/templates/login.html
	}

	@GetMapping("/home-page")
	public String showSeats(Model model) {
		List<Theater> theaters = theaterService.getAllTheaters();
		model.addAttribute("theaters", theaters);
		List<Movie> movies = movieService.getAllMovies();
		model.addAttribute("movies", movies);

		List<String> showTimes = Arrays.asList("10:00 AM", "1:00 PM", "4:00 PM", "7:00 PM", "11:00 PM");
		model.addAttribute("showTimes", showTimes);

		Map<Integer, List<Seat>> seatMap = seatService.generateSeatMap();

		model.addAttribute("seatMap", seatMap);
		return "home-page"; // Maps to src/main/resources/templates/seats.html
	}

	@GetMapping("/purchase")
	public String showPurchaseForm(@RequestParam("seats") String seats,
			@RequestParam("price") String price,
			Model model) {
		// Add the selected seats and total price to the model
		model.addAttribute("selectedSeats", seats);
		model.addAttribute("totalPrice", price);
		return "PurchaseForm"; // Thymeleaf template name
	}

	@GetMapping("/reservation")
	public String showReservationForm(Model model) {
		model.addAttribute("theaters", theaterService.getAllTheaters()); // List<Theater>
		model.addAttribute("movies", movieService.getAllMovies()); // List<Movie>
		return "ReservationForm";
	}

	@GetMapping("/movies")
	public String moviesPage(Model model) {
		model.addAttribute("title", "Movies List");
		return "movies"; // Refers to src/main/resources/templates/movies.html
	}
}
