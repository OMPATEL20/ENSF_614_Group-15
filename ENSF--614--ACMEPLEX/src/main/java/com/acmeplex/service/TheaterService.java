package com.acmeplex.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acmeplex.model.Theater;
import com.acmeplex.repository.TheaterRepository;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    // Retrieve all theaters
    public List<Theater> getAllTheaters() {
        return theaterRepository.getAllTheaters();
    }

    // Retrieve a theater by ID
    public Theater getTheaterById(String theater) {
        return theaterRepository.getTheaterById(theater);
    }

    // Retrieve a theater by name
    public Theater getTheaterByName(String name) {
        return theaterRepository.findByTheaterName(name);
    }

    
}
