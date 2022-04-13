package com.GymApp.GymApp;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

@Controller
public class GymAppController implements ErrorController{
	                                                                                      
	Gym activeGym;

	//@Autowired
	//GymRepository gymRepo;
	
	//@Autowired
	//ExerciseRepository exerciseRepo;
	
	//@Autowired
	//ProgramRepository programRepo; 

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
	public ModelAndView registerComplete(ModelAndView model, @Valid Gym gym, BindingResult bindingResult 
			//, Authority authority
			) {
		Gym existingEmail = programService.findByEmail(gym.getGymEmail());
		
		// Email validation
		if (existingEmail != null) {
			model.addObject("alreadyRegisteredMessage",
					"Oops!  You have already registered with this email.");
			model.setViewName("gym_signup_form");
			bindingResult.reject("gymEmail");
			return model;
		} else {
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
			//gymRepo.save(gym);
			
			// USE THIS ENCODER ON FINAL APP
			gym.setGymPassword(encoder.encode(gym.getGymPassword()));
			gym.setGymPasswordConfirmed(encoder.encode(gym.getGymPassword()));
			
			// TESTING
			//gym.setGymPassword(gym.getGymPassword());
			//gym.setGymPasswordConfirmed(gym.getGymPassword());
			//
			
			
			gym.setEnabled(true);
			gym.setRole("ROLE_USER");
			
			//authority.setGym(gym);
			//authority.setAuthority("USER");
			
			programService.saveGym(gym);
			//programService.saveAuthority(authority);
			
			model.setViewName("gym_signup_complete");
		}
		return model;

	}

	@GetMapping("/login")
	public ModelAndView newLogin(ModelAndView model) {
		model.setViewName("user_login");
		//model.addObject("authGym", new Gym());
		return model;
	}
	
	
	@GetMapping("/about")
	public ModelAndView about(ModelAndView model) {
		model.setViewName("about");
		return model;
	}
	
	/*@GetMapping("/login_page")
	public ModelAndView login(ModelAndView model) {
		model.setViewName("user_login");
		model.addObject("authGym", new Gym());
		return model;
	}*/

	//@GetMapping("/user/main")
	/*@PostMapping("/login_page")
	public ModelAndView loginCheck(Gym authGym, ModelAndView model, BindingResult bindingResult) {
		Gym gymWithSameEmailAddress = programService.findByEmail(authGym.getGymEmail());
		boolean match;
		
		if (gymWithSameEmailAddress != null) {
			//match = encoder.matches(authGym.getGymPassword(), gymWithSameEmailAddress.getGymPassword());
			match = true;
		}
		else
			match = false;
		
		System.out.println(match);
			
		/*Gym checkGym = programService.login(authGym.getGymEmail(), encoder.encode(authGym.getGymPassword()));
		System.out.println(authGym.getGymPassword());*/

	/*	if (match == false) {
			model.addObject("errorMessage", "Invalid email address or password");
			model.addObject("authGym", new Gym());
			model.setViewName("user_login");
			bindingResult.reject("gymEmail");
			bindingResult.reject("gymPassword");
		} else {
			//Program program = new Program();
			//model.addObject("program", program);
			model.setViewName("main_user_page");
			
			activeGym = gymWithSameEmailAddress;
			//model.addObject("gym", activeGym);
		}
		return model;
	}*/
	
	@GetMapping("/user/main")
	public ModelAndView requestNewProgram(ModelAndView model, Gym gym, Principal auth) {
		//gym = activeGym;
		//Program program = new Program(gym);
		
		/*Program program = new Program();
		model.addObject("program", program);*/
		
		activeGym = programService.findByEmail(auth.getName());
		
		model.addObject("name", activeGym.getGymName());
		model.setViewName("main_user_page");
		return model;
	}
	
	@PostMapping("/user/new_program")
	public ModelAndView generateNewProgram(ModelAndView model) {
		//System.out.println(activeGym.toString() + " from post");
		
		UserPreference userPreference = new UserPreference();
		Program program = new Program();
		
		model.addObject("program", program);
		model.addObject("userPreference", userPreference);
		model.setViewName("user_preference");		
		return model;
	}
	
	@PostMapping("/user/view_program")
	public ModelAndView viewProgram(ModelAndView model, UserPreference userPreference, Program program, Principal auth) {
		//Gym activeGym = programService.findByEmail(auth.getName());
		
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
		System.out.println("User Preference list is " + userPreferenceList.toString());
		System.out.println("User Preference list size is " + userPreferenceList.size());
		
		model.setViewName("history");
		//model.addObject("numberOfPrograms", userPreferenceList.size());
		model.addObject("selectedList", new UserPreference());
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
	
	/*@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request, ModelAndView model) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            model.setViewName("error-404");
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            model.addObject("error-500");
	        }
	    }
	    return model;
	}*/
}
