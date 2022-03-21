package com.GymApp.GymApp;

import java.util.List;

public class LowerDay extends Day{
	private List<Exercise> harmstringEx;
	private List<Exercise> glutesEx;
	private String complexity;
	private String type;
	
	public LowerDay(String type) {
		this.type = type;
	}
	
	public LowerDay() {}

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

	public List<Exercise> getHarmstringEx() {
		return harmstringEx;
	}

	public void setHarmstringEx(List<Exercise> harmstringEx) {
		this.harmstringEx = harmstringEx;
	}

	public List<Exercise> getGlutesEx() {
		return glutesEx;
	}

	public void setGlutesEx(List<Exercise> glutesEx) {
		this.glutesEx = glutesEx;
	}

	@Override
	public String toString() {
		return "LowerDay [harmstringEx=" + harmstringEx + ", glutesEx=" + glutesEx + ", complexity=" + complexity
				+ ", type=" + type + "]";
	}	
	
	
}
