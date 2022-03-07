package com.GymApp.GymApp;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.validator.routines.EmailValidator;

@Controller
public class GymAppController {
	                                                                                      
	Gym activeGym;

	@Autowired
	GymRepository gymRepo;
	
	@Autowired
	ExerciseRepository exerciseRepo;
	
	@Autowired
	ProgramRepository programRepo; 

	@Autowired
	CustomGymDetailsService gymService;                                         

	@GetMapping("/")
	public ModelAndView index(ModelAndView model) {
		model.setViewName("index");
		
		//List<Exercise> chest = exerciseRepo.findByComplexityAndTypeAndChest("Beginner", "Isolated");
		//System.out.println(chest.size());
		
		UpperDay dayOne = new UpperDay();
		dayOne.setChestEx(exerciseRepo.findByComplexityAndTypeAndChest("Beginner", "Isolated"));
		dayOne.setByceptsEx(exerciseRepo.findByComplexityAndTypeAndBycepts("Advanced", "Isolated"));
		System.out.println(dayOne.toString());
		
		return model;
	}

	@GetMapping("/register")
	public ModelAndView register(ModelAndView model) {
		model.addObject("gym", new Gym());
		model.setViewName("gym_signup_form");
		return model;
	}

	@PostMapping("/register_complete")
	public ModelAndView registerComplete(ModelAndView model, @Valid Gym gym, BindingResult bindingResult) {
		Gym existingEmail = gymService.findByEmail(gym.getGymEmail());

		// Email validation
		if (existingEmail != null) {
			model.addObject("alreadyRegisteredMessage",
					"Oops!  There is already a user registered with the email provided.");
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
			gymRepo.save(gym);
			model.setViewName("gym_signup_complete");
		}
		return model;

	}

	@GetMapping("/login")
	public ModelAndView login(ModelAndView model) {
		model.setViewName("user_login");
		model.addObject("authGym", new Gym());
		return model;
		
	}

	@PostMapping("/login")
	public ModelAndView loginCheck(Gym authGym, ModelAndView model, BindingResult bindingResult) {
		Gym checkGym = gymService.login(authGym.getGymEmail(), authGym.getGymPassword());

		if (checkGym == null) {
			model.addObject("errorMessage", "Invalid email address or password");
			model.addObject("authGym", new Gym());
			model.setViewName("user_login");
			bindingResult.reject("gymEmail");
			bindingResult.reject("gymPassword");
		} else {
			model.setViewName("main_user_page");
			activeGym = checkGym;
			//model.addObject("gym", activeGym);
		}
		return model;
	}
	
	@GetMapping("/main_user_page")
	public ModelAndView requestNewProgram(ModelAndView model, Gym gym) {
		//gym = activeGym;
		//Program program = new Program(gym);
		Program program = new Program();
		model.addObject("program", program);
		model.setViewName("main_user_page");
		return model;
	}
	
	@PostMapping("/main_user_page")
	public ModelAndView generateNewProgram(ModelAndView model, Program program) {
		//System.out.println(activeGym.toString() + " from post");
		program.setGym(activeGym);
		programRepo.save(program);
		model.setViewName("new_page");		
		return model;
	}
}
