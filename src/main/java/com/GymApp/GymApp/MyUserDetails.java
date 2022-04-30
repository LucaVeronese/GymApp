package com.GymApp.GymApp;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gymEmail;
	private String gymPassword;
	private boolean enabled;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails(Gym gym) {
		this.gymEmail = gym.getGymEmail();
		this.gymPassword = gym.getGymPassword();
		this.enabled = gym.isEnabled();
		this.authorities = Arrays.stream(gym.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	public MyUserDetails(String gymEmail) {
		this.gymEmail = gymEmail;
	}
	
	public MyUserDetails() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return gymPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return gymEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}


}
