package com.acmeplex.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // Map the 'userId' column
    private Long id;

    private String username;
    private String email;
    private String password;
    private String address;
    
    @Column(name = "cardNum", nullable = false) 
    @Size(min = 16, max = 16, message = "Card number must be exactly 16 digits")
    @Pattern(regexp = "\\d{16}", message = "Card number must only contain digits")
    private String cardNum;
    
    
    private String cvvHash;
    
    
    
    

    public void setCvvHash(String cvvHash) {
		this.cvvHash = cvvHash;
	}

    
    
    

	// Setter with hashing for CVV
    public void setCvv(String cvv) {
        this.cvvHash = hashCvv(cvv);
    }

    public String getCvvHash() {
        return cvvHash;
    }

    // Hashing function using SHA-256
    private String hashCvv(String cvv) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(cvv.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing CVV: " + e.getMessage());
        }
    }

    // Other getters and setters
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", cardNum=" + cardNum + "]";
	}
	public User(Long id, String username, String email, String password, String address,
			@Size(min = 16, max = 16, message = "Card number must be exactly 16 digits") @Pattern(regexp = "\\d{16}", message = "Card number must only contain digits") String cardNum) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.cardNum = cardNum;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
    
    
    
}