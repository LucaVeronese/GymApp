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
		return authorities;
	}

	@Override
	public String getPassword() {
		return gymPassword;
	}

	@Override
	public String getUsername() {
		return gymEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}


}
