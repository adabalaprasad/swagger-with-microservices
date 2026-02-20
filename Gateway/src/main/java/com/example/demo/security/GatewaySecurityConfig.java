package com.example.demo.security; 

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.http.HttpMethod; 
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity; 
import org.springframework.security.config.web.server.ServerHttpSecurity; 
import org.springframework.security.web.server.SecurityWebFilterChain; 
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository; 

@Configuration 
@EnableWebFluxSecurity 
public class GatewaySecurityConfig 
{ 
	
	@Bean 
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http)
	{ 
		return http 
				// Disable default security 
				
				.csrf(csrf -> csrf.disable()) 
				.httpBasic(httpBasic -> httpBasic.disable())
				.formLogin(form -> form.disable())
				
				// Stateless (No session)
				
				.securityContextRepository(NoOpServerSecurityContextRepository.getInstance()) 
				
				// Authorization rules 
				
				.authorizeExchange(exchange -> exchange 
						
						// Allow login API 
						
						.pathMatchers("/auth/**","/attendance/login").permitAll()
						// Allow Swagger 
						
						.pathMatchers( "/v3/api-docs/**", 
								"/attendance/v3/api-docs/**", 
								"/student/v3/api-docs/**",
								
								// add this 
								
								"/swagger-ui.html",
								"/swagger-ui/**", 
								"/webjars/**", "/*/v3/api-docs/**" ).permitAll() 
						
						// Everything else requires authentication 
						
						.anyExchange().permitAll() 
						)
				.build(); 
		} 
	
	
}