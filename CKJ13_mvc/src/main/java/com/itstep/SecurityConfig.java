package com.itstep;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//AuthenticationManager - управляет аутентификацией
	//настройка авторизации
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/", "/index").permitAll()
		.antMatchers("/notes/**").authenticated()
		.antMatchers("/admin_page/**").hasRole("ADMIN")
		.antMatchers("/notes", "/admin_page").hasRole("MANAGER")
		.and()
		.formLogin()
		.and()
		.logout()
		.and()
		.csrf().disable();
		
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		UserDetails user1 = User.builder()
				.username("user")
				.password(passwordEncoder().encode("user"))
				.roles("USER")
				.build();
		
		UserDetails user2=User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN")
				.build();
		
		UserDetails user3=User.builder()
				.username("manager")
				.password(passwordEncoder().encode("manager"))
				.roles("MANAGER")
				.build();
		auth.inMemoryAuthentication().withUser(user1).withUser(user2).withUser(user3);
}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
}
