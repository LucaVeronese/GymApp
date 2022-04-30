package com.GymApp.GymApp;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.validator.routines.EmailValidator;

@Controller
public class GymAppController implements ErrorController{
	                                                                                      
	Gym activeGym;

	@Autowired
	ProgramService programService;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@GetMapping("/index")
	public ModelAndView index(ModelAndView model) {
		model.setViewName("index");
		return model;
	}

	@GetMapping("/register")
	public ModelAndView register(ModelAndView model) {
		model.addObject("gym", new Gym());
		model.setViewName("gym_signup_form");
		return model;
	}

	@PostMapping("/register")
	public ModelAndView registerComplete(ModelAndView model, @Valid Gym gym, BindingResult bindingResult) {
		Gym existingEmail = programService.findByEmail(gym.getGymEmail());
		
		// Email validation
		// Check if email already exists
		if (existingEmail != null) {
			model.addObject("alreadyRegisteredMessage",
					"Oops!  You have already registered with this email.");
			model.setViewName("gym_signup_form");
			bindingResult.reject("gymEmail");
			return model;
		} else {
			// email must be of a specific format to be accepted as valid email
			String usedEmail = gym.getGymEmail();
			boolean valid = EmailValidator.getInstance().isValid(usedEmail);
			if (!valid) {
				model.addObject("invalidEmail", "Incorrect email. Please check");
				model.setViewName("gym_signup_form");
				return model;
			}
		}
		
		// passwordConfirmed validation
		if (!gym.getGymPassword().equals(gym.getGymPasswordConfirmed())) {
			model.addObject("invalidConfirmedPassword", "Passwords do not match. Please try again");
			model.setViewName("gym_signup_form");
			return model;
		}

		if (bindingResult.hasErrors()) {
			model.setViewName("gym_signup_form");
		} else {
			// password is valid and not used before
			gym.setGymPassword(encoder.encode(gym.getGymPassword()));
			gym.setGymPasswordConfirmed(encoder.encode(gym.getGymPassword()));

			gym.setEnabled(true);
			gym.setRole("ROLE_USER");
	
			programService.saveGym(gym);
			
			model.setViewName("gym_signup_complete");
		}
		return model;

	}

	@GetMapping("/login")
	public ModelAndView newLogin(ModelAndView model) {
		model.setViewName("user_login");
		return model;
	}
	
	
	@GetMapping("/about")
	public ModelAndView about(ModelAndView model) {
		model.setViewName("about");
		return model;
	}
	
	@GetMapping("/user/main")
	public ModelAndView requestNewProgram(ModelAndView model, Gym gym, Principal auth) {
		activeGym = programService.findByEmail(auth.getName());
		
		model.addObject("name", activeGym.getGymName());
		model.setViewName("main_user_page");
		return model;
	}
	
	@PostMapping("/user/new_program")
	public ModelAndView generateNewProgram(ModelAndView model) {		
		UserPreference userPreference = new UserPreference();
		Program program = new Program();
		
		model.addObject("program", program);
		model.addObject("userPreference", userPreference);
		model.setViewName("user_preference");		
		return model;
	}
	
	@PostMapping("/user/view_program")
	public ModelAndView viewProgram(ModelAndView model, UserPreference userPreference, Program program, Principal auth) {
		
		userPreference.setGym(activeGym);
		program.setGym(activeGym);

		programService.saveUserPreference(userPreference);
		programService.save(program);
		
		List<List<Exercise>> fullProgram = programService.getExercises(userPreference.getFocus(), userPreference.getFitnessLevel(), userPreference.getDaysPerWeek(), userPreference.getGoal());
		String rep = programService.getReps(userPreference.getGoal());
		String set = programService.getSets(userPreference.getGoal());
		
		model.addObject("fullProgram", fullProgram);
		model.addObject("rep", rep);
		model.addObject("set", set);
		
		System.out.println("Size of fullProgram is " + fullProgram.size());
		System.out.println("fullProgram is " + fullProgram.toString());
		
		model.setViewName("new_program");
		return model;
	}
	
	@GetMapping("/user/history")
	public ModelAndView history(ModelAndView model) {
		List<UserPreference> userPreferenceList = programService.getUserPreferences(activeGym);
		
		// Println() for testing
		System.out.println("User Preference list is " + userPreferenceList.toString());
		System.out.println("User Preference list size is " + userPreferenceList.size());
		
		model.setViewName("history");
		model.addObject("numberOfPrograms", userPreferenceList.size());
		model.addObject("list", userPreferenceList);
		return model;
	}

	@PostMapping("/user/view_history")
	public ModelAndView viewHistory(UserPreference userPreference, ModelAndView model) {
		
		System.out.println(userPreference.toString());
		
		List<List<Exercise>> fullProgram = programService.getExercises(userPreference.getFocus(), userPreference.getFitnessLevel(), userPreference.getDaysPerWeek(), userPreference.getGoal());
		String rep = programService.getReps(userPreference.getGoal());
		String set = programService.getSets(userPreference.getGoal());
		
		model.addObject("fullProgram", fullProgram);
		model.addObject("rep", rep);
		model.addObject("set", set);
		
		System.out.println("Size of fullProgram is " + fullProgram.size());
		System.out.println("fullProgram is " + fullProgram.toString());
		
		model.setViewName("new_program");
		return model;
	}
}
