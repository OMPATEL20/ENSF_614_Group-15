package com.acmeplex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acmeplex.model.Showtime;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
}
