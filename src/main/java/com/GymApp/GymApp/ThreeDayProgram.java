package com.GymApp.GymApp;

import java.util.ArrayList;
import java.util.List;

public class ThreeDayProgram {

	private String focus;
	private List<Day> program;
	
	private String complexity;
	private String type;
	
	public ThreeDayProgram(String focus) {
		this.focus = focus;
	}
	
	public ThreeDayProgram(String focus, String complexity, String type) {
		super();
		this.focus = focus;
		this.complexity = complexity;
		this.type = type;
	}

	//per definition, type of exercises in a 3-day program are Compound
		//From this class we need to retrieve the correct exercise (using findBy...) using type of ex (compound
		//and complexity level
		UpperDay ud;
		LowerDay ld;

	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	public List<Day> getProgram() {
		return program;
	}

	/*
	// setProgram set the alternance between lower and upper days based on focus
	public void setProgram(List<Day> program) {
		ud = new UpperDay();
		ld = new LowerDay();
		//UpperDay ud = new UpperDay();
		//LowerDay ld = new LowerDay();
		if (focus.equalsIgnoreCase("upper")) {
			program.add(ud);
			program.add(ld);
			program.add(ud);
		}
		else {
			program.add(ld);
			program.add(ud);
			program.add(ld);
		}
		this.program = program;
	}
	
	public List<Exercise> retrieveExercise(){
		//List<List<Exercise>> exerciseUpper = new ArrayList<List<Exercise>>();
		//List<List<Exercise>> exerciseLower = null;
		List<Exercise> exerciseUpper = new ArrayList<Exercise>();
		
		for (int i = 0; i < program.size(); i++) {
			if(program.get(i).equals(ud)) {
				exerciseUpper.add(ud.getChestEx(complexity, type));
				exerciseUpper.add(ud.getByceptsEx(complexity, type));
			}
			else
				System.out.println("Lower");
		}
		
		return exerciseUpper;
	}
	*/
	
	@Override
	public String toString() {
		return "ThreeDayProgram [focus=" + focus + ", program=" + program + "]";
	}
	
}
