package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth APIs")
public class AuthController {
	
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Student Login",description = "Validates login via college-attendance service and returns JWT")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Login successful"),@ApiResponse(responseCode = "401", description = "Invalid credentials") })
    public LoginResDTO login(@RequestBody StudentRegDTO dto) 
    {
        return authService.login(dto);
    }
    
   
}
