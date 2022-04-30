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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	ProgramService service;
	
	@Autowired
	UserDetailsService userDetailsService;


	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/

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
		
		auth.userDetailsService(userDetailsService);
		
		/*auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select gym_email, gym_password, enabled from gym where gym_email = ? ")
			.authoritiesByUsernameQuery("select gym_email, authority from authorities where gym_email = ? ");*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
			.antMatchers("/user/**").hasRole("USER")
			.and()
			.formLogin().loginPage("/login")
			.defaultSuccessUrl("/user/main")
			.permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
		
		http.csrf().disable();
		
		//USING THIS FOR TESTING
		/*http.authorizeRequests().antMatchers("/").authenticated().anyRequest().permitAll().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/user/main").permitAll().and().logout()
				.logoutSuccessUrl("/").permitAll();*/
		
		//WORKING - TO BE USED FOR FINAL APP
		/*http.authorizeRequests()
			.antMatchers("/user/**").authenticated().and().formLogin()
		.loginPage("/login").defaultSuccessUrl("/user/main").permitAll().and().logout()
		.logoutSuccessUrl("/").permitAll();*/
		
	}
	
}
