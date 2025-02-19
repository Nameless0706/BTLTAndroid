package com.kounrew.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception { 
			return http.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests (auth -> auth
							.requestMatchers("/hello").permitAll()
							.requestMatchers ("/customer/**").authenticated())
			//.anyRequest().authenticated()
			.formLogin(Customizer.withDefaults())
			.build();
	}
}
