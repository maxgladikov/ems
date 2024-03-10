package org.aston.ems.admin_service.config;

import lombok.RequiredArgsConstructor;
import org.aston.ems.admin_service.security.UsernamePasswordAuthenticationProvider;
import org.aston.ems.admin_service.security.filter.AuthenticationLoggingFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UsernamePasswordAuthenticationProvider authenticationProvider;

	@Autowired
	@Qualifier("delegatedAuthenticationEntryPoint")
	AuthenticationEntryPoint authEntryPoint;

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/api/v1/admin/auth/**").permitAll()
					.requestMatchers(HttpMethod.POST,"/api/v1/admin/users/**").hasAnyAuthority("ADMIN")
					.requestMatchers(HttpMethod.DELETE,"/api/v1/admin/users/**").hasAnyAuthority("ADMIN")
					.requestMatchers(HttpMethod.PUT,"/api/v1/admin/users/**").hasAnyAuthority("ADMIN")
				.anyRequest().authenticated()
		)
				.exceptionHandling((exception)-> exception.authenticationEntryPoint(authEntryPoint))
				.httpBasic(withDefaults());
//		http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(authenticationProvider);
	}
	
	@Bean
	InitializingBean initializingBean() {
		return () -> SecurityContextHolder.setStrategyName(
				SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

}
