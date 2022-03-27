package com.GymApp.GymApp;

public class UserPreference {
	
	private String fitnessLevel;
	private int daysPerWeek;
	private String focus;
	private String goal;
	
	public UserPreference() {}

	public UserPreference(String fitnessLevel, int daysPerWeek, String focus, String goal) {
		super();
		this.fitnessLevel = fitnessLevel;
		this.daysPerWeek = daysPerWeek;
		this.focus = focus;
		this.goal = goal;
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
