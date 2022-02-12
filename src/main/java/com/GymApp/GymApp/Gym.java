package com.GymApp.GymApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "Gym")
public class Gym {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gym_id")
	private long gymId;
	
	@Column(name = "gym_name")
	private String gymName;
	
	@Column(name = "gym_email", unique = true, nullable = false)
	private String gymEmail;
	
	@Column(name = "gym_username")
	private String gymUsername;
	
	@Column(name = "gym_password")
	private String gymPassword;
	
	//needs to create a Password and Username field - update database and gym_signup_form
	public Gym() {}

	public Gym(long gymId, String gymName, String gymEmail, String gymUsername, String gymPassword) {
		this.gymId = gymId;
		this.gymName = gymName;
		this.gymEmail = gymEmail;
		this.gymUsername = gymUsername;
		this.gymPassword = gymPassword;
	}
	
	public Gym(String gymName, String gymEmail, String gymUsername, String gymPassword) {
		this.gymName = gymName;
		this.gymEmail = gymEmail;
		this.gymUsername = gymUsername;
		this.gymPassword = gymPassword;
	}

	public long getGymId() {
		return gymId;
	}

	public void setGymId(long gymId) {
		this.gymId = gymId;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public String getGymUsername() {
		return gymUsername;
	}

	public void setGymUsername(String gymUsername) {
		this.gymUsername = gymUsername;
	}

	public String getGymEmail() {
		return gymEmail;
	}

	public void setGymEmail(String gymEmail) {
		this.gymEmail = gymEmail;
	}
	
	public String getGymPassword() {
		return gymPassword;
	}

	public void setGymPassword(String Password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(Password);
		gymPassword = encodedPassword;
	}

	@Override
	public String toString() {
		return "Gym [gymId=" + gymId + ", gymName=" + gymName + ", gymEmail=" + gymEmail + ", gymUsername="
				+ gymUsername + ", gymPassword=" + gymPassword + "]";
	}
   	
}
