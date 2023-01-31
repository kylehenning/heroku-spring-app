package com.gcu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gcu.business.UserBusinessServiceInterface;

/**
 * This is the config file for SpringSecurity
 * @author connorrolstad
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserBusinessServiceInterface userService;
	
	// Configures the application to use Spring Security and sets configuration rules
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.httpBasic()//This is for future Rest APIs
				.and()
				.authorizeRequests()
				.antMatchers("/service/**").authenticated()
				.and()
			.authorizeRequests()
				.antMatchers("/", "/register/**", "/css/**", "/js/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login/")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				.defaultSuccessUrl("/products/", true)
				.and()
			.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.permitAll()
				.logoutSuccessUrl("/login/");
	}
	
	// Configured using form based authentication with our MySQL database
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
}
