package com.GymApp.GymApp;

import java.util.List;

public class UpperDay {
	private List<Exercise> chestEx;
	private List<Exercise> byceptsEx;
	private String complexity;
	private String type;
	
	public UpperDay(String type) {
		this.type = type;
	}
	
	public UpperDay() {}

	public List<Exercise> getChestEx() {
		return chestEx;
	}

	public void setChestEx(List<Exercise> chestEx) {
		this.chestEx = chestEx;
	}

	public List<Exercise> getByceptsEx() {
		return byceptsEx;
	}

	public void setByceptsEx(List<Exercise> byceptsEx) {
		this.byceptsEx = byceptsEx;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UpperDay [chestEx=" + chestEx + ", byceptsEx=" + byceptsEx + "]";
	}
	
	
}
