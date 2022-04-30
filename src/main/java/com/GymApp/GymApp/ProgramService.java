package com.GymApp.GymApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

	@Autowired
	ProgramRepository programRepo;

	@Autowired
	ExerciseRepository exerciseRepo;

	@Autowired
	GymRepository repo;

	@Autowired
	UserPreferenceRepository userPreferenceRepo;

	public void save(Program program) {
		programRepo.save(program);
	}

	public List<List<Exercise>> getExercises(String focus, String complexity, int days, String goal) {

		String type;
		if (days == 3)
			type = "Compound";
		else
			type = "Isolated";

		List<List<Exercise>> program = new ArrayList<List<Exercise>>();
		return program = setProgram(program, focus, complexity, type, days, goal);
	}

	public List<List<Exercise>> setProgram(List<List<Exercise>> program, String focus, String complexity, String type,
			int days, String goal) {

		if (focus.equalsIgnoreCase("upper")) {
			program.add(getUpperDay(complexity, type, goal));
		}
		else
			program.add(getLowerDay(complexity, type));

		program.add(getLowerDay(complexity, type));
		program.add(getUpperDay(complexity, type, goal));

		if (days == 4) {
			// We can set the splitDay as a "Compound" day
			program.add(getSplitDay(complexity, "Compound"));
		}
		
		if (days == 5) {
			program.add(getLowerDay(complexity, type));
			program.add(getUpperDay(complexity, type, goal));
		}

		return program;
	}

	public List<Exercise> getUpperDay(String complexity, String type, String goal) {
		List<Exercise> day = new ArrayList<Exercise>();
		day.add(getExercise(complexity, type, "Chest"));
		day.add(getExercise(complexity, type, "Tricep"));

		// per definition, there are no Compound bicep exercises. All bicep ex are of type Isolated
		day.add(getExercise(complexity, "Isolated", "Bicep"));
		day.add(getExercise(complexity, type, "Back"));
		day.add(getExercise(complexity, type, "Shoulder"));
		day.add(getExercise(complexity, type, "Core"));

		if (goal.equalsIgnoreCase("Lose")) {
			day.add(getExercise(complexity, "Compound", "Cardio"));
		}

		return day;
	}

	public List<Exercise> getLowerDay(String complexity, String type) {
		List<Exercise> day = new ArrayList<Exercise>();
		for (int i = 0; i < 2; i++) {
			day.add(getExercise(complexity, type, "Quad"));
			day.add(getExercise(complexity, type, "Hamstring"));
			day.add(getExercise(complexity, type, "Glut"));
		}
		day.add(getExercise(complexity, "Isolated", "Calf"));

		return day;
	}

	public List<Exercise> getSplitDay(String complexity, String type) {
		List<Exercise> day = new ArrayList<Exercise>();

		day.add(getExercise(complexity, type, "Chest"));
		day.add(getExercise(complexity, type, "Tricep"));
		day.add(getExercise(complexity, "Isolated", "Bicep"));
		day.add(getExercise(complexity, type, "Back"));
		day.add(getExercise(complexity, type, "Shoulder"));
		day.add(getExercise(complexity, type, "Quad"));
		day.add(getExercise(complexity, type, "Hamstring"));
		day.add(getExercise(complexity, type, "Glut"));

		return day;
	}

	public Exercise getExercise(String complexity, String type, String focus) {
		Exercise exercise = exerciseRepo.findByComplexityAndTypeAndFocus(complexity, type, focus);
		return exercise;
	}

	public String getReps(String goal) {
		if (goal.equalsIgnoreCase("gain"))
			return "6-8";
		else if (goal.equalsIgnoreCase("maintain"))
			return "8-10";
		else
			return "10-12";
	}

	public String getSets(String goal) {
		if (goal.equalsIgnoreCase("gain"))
			return "4";
		else
			return "5";
	}

	public List<Program> history(Gym gym) {
		List<Program> list = programRepo.findByGym(gym);
		System.out.println(list.size());
		return list;
	}

	public List<UserPreference> getUserPreferences(Gym gym) {
		List<UserPreference> day = new ArrayList<UserPreference>();

		day.addAll(userPreferenceRepo.findByGym(gym));
		return day;
	}

	public Gym findByEmail(String email) {
		return repo.findByEmail(email);
	}

	public void saveGym(Gym gym) {
		repo.save(gym);
	}

	public void saveUserPreference(UserPreference up) {
		userPreferenceRepo.save(up);
	}

	public Gym login(String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}
}
