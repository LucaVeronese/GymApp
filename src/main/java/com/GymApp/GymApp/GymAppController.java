package com.GymApp.GymApp;

import java.util.ArrayList;
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

	//@Autowired
	//GymRepository gymRepo;
	
	//@Autowired
	//ExerciseRepository exerciseRepo;
	
	//@Autowired
	//ProgramRepository programRepo; 

	@Autowired
	CustomGymDetailsService gymService;                                         
	
	@Autowired
	ProgramService programService;

	@GetMapping("/")
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
			//gymRepo.save(gym);
			gymService.saveGym(gym);
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
		//programRepo.save(program);
		programService.save(program);
		model.setViewName("new_page");		
		return model;
	}
	
	@GetMapping("/test")
	public ModelAndView test(ModelAndView model) {

		//List<Exercise> chest = exerciseRepo.findByComplexityAndTypeAndChest("Beginner", "Isolated");
		//System.out.println(chest.size());
		
		/*UpperDay dayOne = new UpperDay();
		String type = "Isolated";
		dayOne.setChestEx(exerciseRepo.findByComplexityAndTypeAndChest("Beginner", type));
		dayOne.setByceptsEx(exerciseRepo.findByComplexityAndTypeAndBycepts("Advanced", type));
		System.out.println(dayOne.toString());*/
		
		List<Day> program = new ArrayList<Day>();
		/*ThreeDayProgram tdp = new ThreeDayProgram("upper", "beginner", "isolated");
		tdp.setProgram(program);
		List<Exercise> list = tdp.retrieveExercise();
		System.out.println(list.toString());
		System.out.println(tdp.toString());*/
		
		List<List<Exercise>> fullProgram = programService.getExercises(program, "upper", "Beginner", "Isolated", 3);
		
		model.addObject("fullProgram", fullProgram);
		System.out.println("Size of fullProgram is " + fullProgram.size());
		
		model.setViewName("test");
		return model;
		
	}
}
