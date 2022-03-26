package com.GymApp.GymApp;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerGymDetails extends Gym implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Gym gym;

	public CustomerGymDetails(Gym gym) {
		this.gym = gym;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //
		return null;
	}

	@Override
	public String getPassword() { // TODO Auto-generated method stub
		return gym.getGymPassword();
	}

	@Override
	public String getUsername() { // TODO Auto-generated method stub
		return gym.getGymUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // TODO Auto-generated
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // TODO Auto-generated method
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // TODO Auto-generated
		return true;
	}

	@Override
	public boolean isEnabled() { // TODO Auto-generated method stub
		return true;

	}
}
