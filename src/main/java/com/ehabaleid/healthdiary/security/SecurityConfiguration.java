package com.ehabaleid.healthdiary.security;

import com.ehabaleid.healthdiary.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AppUserDetailsService appUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/js/**", "/css/**", "/scss/**", "/").permitAll()
				.antMatchers("/admin").hasAnyAuthority("ADMIN")
				.antMatchers("/logfood").hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/dashboard").hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/logmemory").hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/").hasAnyAuthority("USERS", "ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin().permitAll()
				.loginPage("/login")
				.defaultSuccessUrl("/dashboard", true);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
