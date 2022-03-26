package com.GymApp.GymApp;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.validator.routines.EmailValidator;

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
		Gym existingEmail = programService.findByEmail(gym.getGymEmail());
		
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
			gym.setGymPassword(encoder.encode(gym.getGymPassword()));
			gym.setGymPasswordConfirmed(encoder.encode(gym.getGymPassword()));
			programService.saveGym(gym);
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
		Gym gymWithSameEmailAddress = programService.findByEmail(authGym.getGymEmail());
		boolean match;
		
		if (gymWithSameEmailAddress != null) {
			match = encoder.matches(authGym.getGymPassword(), gymWithSameEmailAddress.getGymPassword());
		}
		else
			match = false;
			
		/*Gym checkGym = programService.login(authGym.getGymEmail(), encoder.encode(authGym.getGymPassword()));
		System.out.println(authGym.getGymPassword());*/

		if (match == false) {
			model.addObject("errorMessage", "Invalid email address or password");
			model.addObject("authGym", new Gym());
			model.setViewName("user_login");
			bindingResult.reject("gymEmail");
			bindingResult.reject("gymPassword");
		} else {
			model.setViewName("main_user_page");
			activeGym = gymWithSameEmailAddress;
			//model.addObject("gym", activeGym);
		}
		return model;
	}
	
	@GetMapping("/user/main_page")
	public ModelAndView requestNewProgram(ModelAndView model, Gym gym) {
		//gym = activeGym;
		//Program program = new Program(gym);
		Program program = new Program();
		model.addObject("program", program);
		model.setViewName("main_user_page");
		return model;
	}
	
	@PostMapping("/user/main_page")
	public ModelAndView generateNewProgram(ModelAndView model, Program program) {
		//System.out.println(activeGym.toString() + " from post");
		program.setGym(activeGym);
		//programRepo.save(program);
		programService.save(program);
		model.setViewName("new_page");		
		return model;
	}
	
	@GetMapping("/user/test")
	public ModelAndView test(ModelAndView model) {
		
		List<List<Exercise>> fullProgram = programService.getExercises("upper", "Beginner", "Isolated", 3);
		
		model.addObject("fullProgram", fullProgram);
		System.out.println("Size of fullProgram is " + fullProgram.size());
		System.out.println("fullProgram is " + fullProgram.toString());
		
		
		model.setViewName("test");
		return model;
	}
	
	@RequestMapping("/error")
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
	}
}
