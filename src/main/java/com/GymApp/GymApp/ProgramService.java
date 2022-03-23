package com.GymApp.GymApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ProgramService implements UserDetailsService {

	/*private UpperDay ud;
	private LowerDay ld;*/

	@Autowired
	ProgramRepository programRepo;

	@Autowired
	ExerciseRepository exerciseRepo;
	
	private Random rand = new Random();

	public void save(Program program) {
		programRepo.save(program);
	}

	public List<List<Exercise>> getExercises(String focus, String complexity, String type,
			int days) {
		List<List<Exercise>> program = new ArrayList<List<Exercise>>();
		return program = setProgram(program, focus, complexity, type, days);
		//return retrieveExercise(prog, complexity, type);
	}

	public List<List<Exercise>> setProgram(List<List<Exercise>> program, String focus, String complexity, String type, int days) {
		
		if (focus.equalsIgnoreCase("upper"))
			program.add(getUpperDay(complexity, type));
		else 
			//program.add(getLowerDay(complexity, type));
		
		//program.add(getLowerDay(complexity, type));
		program.add(getUpperDay(complexity, type));
		
		if (days == 4) {
			//program.add(getSplitDay(complexity, type));
		}
		if (days == 5) {
			//program.add(getLowerDay(complexity, type));
			program.add(getUpperDay(complexity, type));
		}
		
		return program;
	}
	
	public List<Exercise> getUpperDay (String complexity, String type){
		List<Exercise> day = new ArrayList<Exercise>();
		day.add(getExercise(complexity, type, "Chest"));
		//day.add(getExercise(complexity, type, "Tricep"));
		day.add(getExercise(complexity, type, "Bicep"));
		/*day.add(getExercise(complexity, type, "Back"));
		day.add(getExercise(complexity, type, "Shoulder"));
		day.add(getExercise(complexity, type, "Core"));*/
		
		return day;
	}
	
	public List<Exercise> getLowerDay (String complexity, String type){
		List<Exercise> day = new ArrayList<Exercise>();
		for (int i = 0; i < 2; i++) {
			day.add(getExercise(complexity, type, "Quad"));
			day.add(getExercise(complexity, type, "Hamstring"));
			day.add(getExercise(complexity, type, "Glut"));
		}
		day.add(getExercise(complexity, type, "Calf"));
		
		return day;
	}
	
	public List<Exercise> getSplitDay (String complexity, String type){
		List<Exercise> day = new ArrayList<Exercise>();

		day.add(getExercise(complexity, type, "Chest"));
		//day.add(getExercise(complexity, type, "Tricep"));
		day.add(getExercise(complexity, type, "Bicep"));
		/*day.add(getExercise(complexity, type, "Back"));
		day.add(getExercise(complexity, type, "Shoulder"));
		day.add(getExercise(complexity, type, "Quad"));
		day.add(getExercise(complexity, type, "Hamstring"));
		day.add(getExercise(complexity, type, "Glut"));*/

		return day;
	}

	public Exercise getExercise(String complexity, String type, String focus) {
		List<Exercise> list = exerciseRepo.findByComplexityAndTypeAndFocus(complexity, type, focus);
		if(list.size() >= 1) {
			Exercise exercise = list.get(rand.nextInt(list.size()));
			return exercise;
		}
		else return null;
	}
	
	/*public Exercise bicepExercise(String complexity) {
		List<Exercise> list = exerciseRepo.findByComplexityAndFocus(complexity, "Bicep");
		if(list.size() >= 1) {
			Exercise exercise = list.get(rand.nextInt(list.size()));
			return exercise;
		}
		else return null;
	}
	
	public Exercise tricepExercise(String complexity, String type) {
		List<Exercise> list = exerciseRepo.findByComplexityAndTypeAndFocus(complexity, type, "Tricep");
		if(list.size() >= 1) {
			Exercise exercise = list.get(rand.nextInt(list.size()));
			return exercise;
		}
		else return null;
	}
	
	public Exercise backExercise(String complexity, String type) {
		List<Exercise> list = exerciseRepo.findByComplexityAndTypeAndFocus(complexity, type, "Back");
		if(list.size() >= 1) {
			Exercise exercise = list.get(rand.nextInt(list.size()));
			return exercise;
		}
		else return null;
	}
	
	public Exercise shoulderExercise(String complexity, String type) {
		List<Exercise> list = exerciseRepo.findByComplexityAndTypeAndFocus(complexity, type, "Back");
		if(list.size() >= 1) {
			Exercise exercise = list.get(rand.nextInt(list.size()));
			return exercise;
		}
		else return null;
	}*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
