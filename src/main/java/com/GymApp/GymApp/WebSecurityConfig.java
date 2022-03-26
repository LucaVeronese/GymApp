package com.GymApp.GymApp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	ProgramService service;

	/*@Bean
	public UserDetailsService userDetailsService() {
		return new CustomGymDetailsService();
	}*/

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	public CustomGymDetailsService gymService() {
		return new CustomGymDetailsService();
	}*/

	/*@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());

		return authProvider;
	}*/

	//@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*http.authorizeRequests().antMatchers("/").authenticated().anyRequest().permitAll().and().formLogin()
				.loginPage("/user_login").defaultSuccessUrl("/main_user_page").permitAll().and().logout()
				.logoutSuccessUrl("/").permitAll();*/
		
		// WORKING
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated().and().formLogin()
		.loginPage("/login").defaultSuccessUrl("/main_user_page").permitAll().and().logout()
		.logoutSuccessUrl("/").permitAll();
		
		http.csrf().disable();
	}

}
