package com.acmeplex.model;


import java.util.LinkedHashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "theater") // Ensure this matches the database table name
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theaterId") // Primary key with auto-increment
    private int theaterId;

    // @Column(nullable = false, name = "theaterName")
    private String theaterName;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LinkedHashSet<Showtime> showtimes = new LinkedHashSet<>();

    // Constructors
    public Theater() {}

    public Theater(String name) {
        this.theaterName = name;
    }

    public Theater(int int1, String string) {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
    public int getId() {
        return theaterId;
    }

    public void setId(int id) {
        this.theaterId = id;
    }

    public String getName() {
        return theaterName;
    }

    public void setName(String name) {
        this.theaterName = name;
    }

    public LinkedHashSet<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(LinkedHashSet<Showtime> showtimes) {
        this.showtimes = showtimes;
    }
}


// package com.acmeplex.models;

// public class Theater {
//     private int theaterId;
//     private String theaterName;

//     // Constructors
//     public Theater() {}

//     public Theater(int id, String name) {
//         this.theaterId = id;
//         this.theaterName = name;
//     }

//     // Getters and Setters
//     public int getId() {
//         return theaterId;
//     }

//     public void setId(int id) {
//         this.theaterId = id;
//     }

//     public String getName() {
//         return theaterName;
//     }

//     public void setName(String name) {
//         this.theaterName = name;
//     }
// }



// package com.acmeplex.models;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.LinkedHashSet;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "theater")
// public class Theater {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private int theaterId;
	
//     private String theaterName;
// 	// show times already contains movieId and TheaterId

//     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "theater")
// 	private LinkedHashSet<Showtime> showtimes = new LinkedHashSet<>();
	
	
// 	/**
// 	 * Theater constructor that assigns id.
// 	 * @param theaterId (int) id assigned to the theater
// 	 */
// 	public Theater(int theaterId, String name) {
// 		this.setTheaterId(theaterId);
// 		this.setTheaterName(name);
// 	}
	
// 	public void addShowTimes(Showtime showtime) {
// 		if(this.theaterId!=showtime.getAuditorium()) {
// 			System.out.println("theaterId must match before adding");
// 			return;
// 		}
// 		// if not match time
// 		if(!this.showtimes.stream().anyMatch(t->t.getTime().equals(showtime.getTime()))) {
// 			this.showtimes.add(showtime);
// 		}
// 	}
	
	
// 	public void removeShowTimes(Showtime showtime) {
// 		if(this.theaterId!=showtime.getAuditorium()) {
// 			System.out.println("theaterId must match before adding");
// 			return;
// 		}
// 		// if match time
// 		if(this.showtimes.stream().anyMatch(t->t.getTime().equals(showtime.getTime()))) {
// 			this.showtimes.remove(showtime);
// 		}	
// 	}
	
	
// 	/**
// 	 * Cancel a reservation for a specified seat number and showtime.
// 	 */
// 	// public void resetReservation(int movieId, LocalDateTime time, int colNum, int rowNum) {
// 	// 	for (Showtime showtime : showtimes) {
// 	// 		if (showtime.getMovieId() == movieId && 
// 	// 				showtime.getTime().isEqual(time))
// 	// 			showtime.resetReservation(rowNum, colNum);
// 	// 	}
// 	// }
	
// 	/**
// 	 * Reserve a seat for a specified show time and seat number.
// 	 */
// 	// public void reserveSeat(int movieId, LocalDateTime time, int colNum, int rowNum) {
// 	// 	for (Showtime showtime : showtimes) {
// 	// 		if (showtime.getMovieId() == movieId && 
// 	// 				showtime.getTime().isEqual(time))
// 	// 			showtime.reserveSeat(rowNum, colNum);
// 	// 	}
// 	// }
	


// 	/**
// 	 * Search for all showtimes where the theaterId matches the searched id.
// 	 */
// 	public ArrayList<Showtime> searchShowtimes(int theaterId) {
// 		ArrayList<Showtime> foundShowtimes = new ArrayList<Showtime>();
		
// 		for (Showtime show : this.getShowtimes()) {
// 			if (show.getAuditorium() == theaterId)
// 				foundShowtimes.add(show);
// 		}
// 		return foundShowtimes;
// 	}


// 	public int getTheaterId() {
//         return theaterId;
//     }

//     public void setTheaterId(int theaterId) {
//         this.theaterId = theaterId;
//     }

//     public String getTheaterName() {
//         return theaterName;
//     }

//     public void setTheaterName(String theaterName) {
//         this.theaterName = theaterName;
//     }

//     public LinkedHashSet<Showtime> getShowtimes() {
//         return showtimes;
//     }

//     public void setShowtimes(LinkedHashSet<Showtime> showtimes) {
//         this.showtimes = showtimes;
//     }
// }

