package net.javaguide.springboot.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SceurityConfig {
	
      private UserDetailsService userDetailsService;
      
	  public SceurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	  
	  @Bean
		public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		    return http.getSharedObject(AuthenticationManagerBuilder.class)
		            .build();
		}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	 return configuration.getAuthenticationManager();
    }
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests((authorize) -> 
		 
//authorize.anyRequest().authenticated()).//this line used for basic and form based authentication
		 authorize
		.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
		//.requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
		.requestMatchers("/api/auth/**").permitAll()
		.anyRequest()
		.authenticated())
		.httpBasic(Customizer.withDefaults());
		return http.build();
		
	}
	
	//This is for form bsed Authentication in memory authentication is not required
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails sonu = User.builder()
//				.username("sonu")
//				.password(passwordEncoder().encode("sonu"))
//				.roles("USER")
//				.build();
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("admin"))
//				.roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(sonu,admin);
//	}

}
