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
public class ProgramService {

	/*private UpperDay ud;
	private LowerDay ld;*/

	@Autowired
	ProgramRepository programRepo;

	@Autowired
	ExerciseRepository exerciseRepo;
	
	@Autowired
	GymRepository repo;
	
	@Autowired
	UserPreferenceRepository userPreferenceRepo;
	
	/*@Autowired
	AuthorityRepository authorityRepo;*/
	
	private Random rand = new Random();

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
		//return retrieveExercise(prog, complexity, type);
	}

	public List<List<Exercise>> setProgram(List<List<Exercise>> program, String focus, String complexity, String type, int days, String goal) {
		
		if (focus.equalsIgnoreCase("upper")) {
			program.add(getUpperDay(complexity, type));
			/*if (goal.equalsIgnoreCase("Lose")) {
				program.add(getCardioDay());
			}*/
		}
		else 
			//program.add(getLowerDay(complexity, type));
		
		//program.add(getLowerDay(complexity, type));
		program.add(getUpperDay(complexity, type));
		
		//TEST
		program.add(getUpperDay(complexity, type));
		program.add(getUpperDay(complexity, type));
		//END TEST
		
		if (days == 4) {
			// We can set the splitDay as a "Compound" day
			//program.add(getSplitDay(complexity, "Compound"));
			/*if (goal.equalsIgnoreCase("Lose")) {
				program.add(getCardioDay());
			}*/
		}
		if (days == 5) {
			//program.add(getLowerDay(complexity, type));
			program.add(getUpperDay(complexity, type));
			/*if (goal.equalsIgnoreCase("Lose")) {
				program.add(getCardioDay());
			}*/
		}
		
		return program;
	}
	
	public List<Exercise> getUpperDay (String complexity, String type){
		List<Exercise> day = new ArrayList<Exercise>();
		day.add(getExercise(complexity, type, "Chest"));
		//day.add(getExercise(complexity, type, "Tricep"));
		
		// per definition, there are no Compound bicep exercise. All bicep ex are of type Isolated
		day.add(getExercise(complexity, "Isolated", "Bicep"));
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
		day.add(getExercise(complexity, "Isolated", "Bicep"));
		/*day.add(getExercise(complexity, type, "Back"));
		day.add(getExercise(complexity, type, "Shoulder"));
		day.add(getExercise(complexity, type, "Quad"));
		day.add(getExercise(complexity, type, "Hamstring"));
		day.add(getExercise(complexity, type, "Glut"));*/

		return day;
	}
	
	public List<Exercise> getCardioDay(){
		//TO DO
		return null;
	}

	public Exercise getExercise(String complexity, String type, String focus) {
		List<Exercise> list = exerciseRepo.findByComplexityAndTypeAndFocus(complexity, type, focus);
		if(list.size() >= 1) {
			Exercise exercise = list.get(rand.nextInt(list.size()));
			return exercise;
		}
		else return null;
	}
	
	public String getReps(String goal) {
		if(goal.equalsIgnoreCase("gain"))
			return "6-8";
		else if(goal.equalsIgnoreCase("maintain"))
			return "8-10";
		else
			return "10-12";
	}
	
	public String getSets(String goal) {
		if(goal.equalsIgnoreCase("gain"))
			return "4";
		else
			return "5";		
	}
	
	public List<Program> history(Gym gym){
		List<Program> list = programRepo.findByGym(gym);
		System.out.println(list.size());
		return list;
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
	
	public Gym findByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public void saveGym(Gym gym) {
		repo.save(gym);
	}
	
	/*public void saveAuthority(Authority auth) {
		authorityRepo.save(auth);
	}*/
	
	public void saveUserPreference(UserPreference up) {
		userPreferenceRepo.save(up);
	}
	
	public Gym login (String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}

	// during registration, email cannot be already in database
	/*public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Gym gym = repo.findByUsername(email);
		if (gym == null) {
			throw new UsernameNotFoundException("Gym not found");
		}

		return new MyUserDetails(gym);
	}*/
}
