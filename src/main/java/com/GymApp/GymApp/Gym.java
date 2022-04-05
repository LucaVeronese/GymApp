package com.GymApp.GymApp;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.Name;

@Entity
@Table(name = "Gym")
public class Gym { 
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gym_id")
	private long gymId;*/
	
	@NotBlank
	@Column(name = "gym_name")
	private String gymName;
	
	@Id
	@NotBlank
	@Column(name = "gym_email")
	//@Email(message = "Invalid email.")
	private String gymEmail;
	
	@NotBlank
	@Column(name = "gym_username")
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
	private String gymUsername;
	
	@Column(name = "gym_password")
	@ValidPassword
	private String gymPassword;
	
	private String gymPasswordConfirmed;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "role")
	private String role;

	//@ColumnDefault(value = "USER")
	/*@Column(columnDefinition = "varchar(255) default USER")
	private String enabled;*/
	
	//@OneToMany(targetEntity = Program.class, cascade = CascadeType.ALL, orphanRemoval = true)
	//@JoinColumn(name = "gp_fk", referencedColumnName = "gymId")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gym")
	private List<Program> programs;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gym")
	private List<UserPreference> userPreferences;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "gym")
	private List<Authority> authorities;*/
	
	//needs to create a Password and Username field - update database and gym_signup_form
	public Gym() {}

	public Gym(long gymId, String gymName, String gymEmail, String gymUsername, String gymPassword, String gymPasswordConfirmed) {
		//this.gymId = gymId;
		this.gymName = gymName;
		this.gymEmail = gymEmail;
		this.gymUsername = gymUsername;
		this.gymPassword = gymPassword;
		this.gymPasswordConfirmed = gymPasswordConfirmed;
	}
	
	public Gym(String gymName, String gymEmail, String gymUsername, String gymPassword, String gymPasswordConfirmed) {
		this.gymName = gymName;
		this.gymEmail = gymEmail;
		this.gymUsername = gymUsername;
		this.gymPassword = gymPassword;
		this.gymPasswordConfirmed = gymPasswordConfirmed;
	}
	
	public Gym(Gym gym) {
		this.gymName = gym.getGymName();
		this.gymEmail = gym.getGymEmail();
		this.gymUsername = gym.getGymUsername();
		this.gymPassword = gym.getGymPassword();
		this.gymPasswordConfirmed = gym.getGymPasswordConfirmed();
	}

	/*public long getGymId() {
		return gymId;
	}

	public void setGymId(long gymId) {
		this.gymId = gymId;
	}*/

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
		/*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(Password);
		gymPassword = encodedPassword;*/
		this.gymPassword = Password;
	}

	public String getGymPasswordConfirmed() {
		return gymPasswordConfirmed;
	}

	public void setGymPasswordConfirmed(String gymPasswordConfirmed) {
		this.gymPasswordConfirmed = gymPasswordConfirmed;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public List<UserPreference> getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(List<UserPreference> userPreferences) {
		this.userPreferences = userPreferences;
	}

	/*@Override
	public String toString() {
		return "Gym [gymId=" + gymId + ", gymName=" + gymName + ", gymEmail=" + gymEmail + ", gymUsername="
				+ gymUsername + ", gymPassword=" + gymPassword + "]";
	}*/
   	
}
