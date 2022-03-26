package com.GymApp.GymApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*@Service
public class CustomGymDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	GymRepository repo;
	
	public Gym findByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public void saveGym(Gym gym) {
		repo.save(gym);
	}
	
	public Gym login (String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}

	@Override
	// during registration, email cannot be already in database
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Gym gym = repo.findByUsername(email);
		if (gym == null) {
			throw new UsernameNotFoundException("Gym not found");
		}

		return new CustomerGymDetails(gym);
	}*/
