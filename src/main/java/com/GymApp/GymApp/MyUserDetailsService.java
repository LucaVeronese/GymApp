package com.GymApp.GymApp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	GymRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String gymEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Gym> gym = repo.findByUsername(gymEmail);
		gym.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
		return gym.map(MyUserDetails::new).get();
	}

}
