package com.GymApp.GymApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GymAppController {

	@Autowired
	GymRepository gymRepo;

	@GetMapping("")
	public String viewHome() {
		return "index.html";
	}

	@GetMapping("/register")
	public String showGymSignUpForm(Model model) {
		model.addAttribute("gym", new Gym());
		return "gym_signup_form";
	}

	@PostMapping("/register_complete")
	public String showCompleteGymSignup(Gym gym) {
		gymRepo.save(gym);

		return "gym_signup_complete";
	}
}
