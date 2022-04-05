package com.GymApp.GymApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_preference")
public class UserPreference {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userPreferenceId;
	
	@Column(name="fitness_level")
	private String fitnessLevel;
	
	@Column(name="days_per_week")
	private int daysPerWeek;
	
	@Column(name="focus")
	private String focus;
	
	@Column(name="goal")
	private String goal;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_email")
	private Gym gym;
	
	public UserPreference() {}

	public UserPreference(String fitnessLevel, int daysPerWeek, String focus, String goal) {
		super();
		this.fitnessLevel = fitnessLevel;
		this.daysPerWeek = daysPerWeek;
		this.focus = focus;
		this.goal = goal;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public String getFitnessLevel() {
		return fitnessLevel;
	}

	public void setFitnessLevel(String fitnessLevel) {
		this.fitnessLevel = fitnessLevel;
	}

	public int getDaysPerWeek() {
		return daysPerWeek;
	}

	public void setDaysPerWeek(int daysPerWeek) {
		this.daysPerWeek = daysPerWeek;
	}

	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	@Override
	public String toString() {
		return "UserPreference [fitnessLevel=" + fitnessLevel + ", daysPerWeek=" + daysPerWeek + ", focus=" + focus
				+ ", goal=" + goal + "]";
	}
}
