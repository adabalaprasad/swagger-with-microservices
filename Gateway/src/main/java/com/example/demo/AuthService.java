package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.security.JwtUtil;

import reactor.core.publisher.Mono;

@Service
public class AuthService {


	@Autowired
    RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) 
    {
        this.restTemplate = restTemplate;
    }

    public LoginResDTO login(StudentRegDTO dto) {

        String url = "http://localhost:2002/login";

        LoginResDTO res =restTemplate.postForObject(url, dto, LoginResDTO.class);

        if (res != null && "OK".equalsIgnoreCase(res.getStatus())) {
            String token = JwtUtil.generateToken(dto.getEmail());
            res.setToken(token);
        }

        return res;
    }
}

