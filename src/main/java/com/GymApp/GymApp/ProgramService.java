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

	private UpperDay ud;
	private LowerDay ld;

	@Autowired
	ProgramRepository programRepo;

	@Autowired
	ExerciseRepository exerciseRepo;
	
	private Random rand = new Random();

	public void save(Program program) {
		programRepo.save(program);
	}

	public List<List<Exercise>> getExercises(List<Day> program, String focus, String complexity, String type,
			int days) {
		List<Day> prog = setProgram(program, focus);
		return retrieveExercise(prog, complexity, type);
	}

	public List<Day> setProgram(List<Day> program, String focus) {
		ud = new UpperDay();
		ld = new LowerDay();
		// UpperDay ud = new UpperDay();
		// LowerDay ld = new LowerDay();
		if (focus.equalsIgnoreCase("upper")) {
			program.add(ud);
			program.add(ld);
			program.add(ud);
		} else {
			program.add(ld);
			program.add(ud);
			program.add(ld);
		}
		return program;
	}

	public List<List<Exercise>> retrieveExercise(List<Day> program, String complexity, String type) {

		List<Exercise> day;
		
		List<List<Exercise>> list = new ArrayList<List<Exercise>>();

		for (int i = 0; i < program.size(); i++) {
			
			day = new ArrayList<Exercise>();
			
			if (program.get(i).equals(ud)) {
				day.add(chestExercise(complexity, type));
				day.add(bicepExercise(complexity));
			} else {
				day.add(chestExercise(complexity, type));
				day.add(bicepExercise(complexity));
			}
			list.add(day);
		}
		
		System.out.println(list.size());
		
		return list;
		//return exerciseUpper;
	}

	public Exercise chestExercise(String complexity, String type) {
		List<Exercise> list = exerciseRepo.findByComplexityAndTypeAndChest(complexity, type);
		if(list.size() >= 1) {
			Exercise exercise = list.get(rand.nextInt(list.size()));
			//System.out.println(exercise.toString());

			return exercise;
		}
		else return null;
	}
	
	public Exercise bicepExercise(String complexity) {
		List<Exercise> list = exerciseRepo.findByComplexityAndBicep(complexity);
		if(list.size() >= 1) {
			Exercise exercise = list.get(rand.nextInt(list.size()));
			//System.out.println(exercise.toString());

			return exercise;
		}
		else return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
