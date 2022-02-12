
package com.GymApp.GymApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomGymDetailsService implements UserDetailsService {

	@Autowired
	GymRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Gym gym = repo.findGymByUsername(username);
		if (gym == null) {
			throw new UsernameNotFoundException("Gym not found");
		}

		return new CustomerGymDetails(gym);
	}

}
