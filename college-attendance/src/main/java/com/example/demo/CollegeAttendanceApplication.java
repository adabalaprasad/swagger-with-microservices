package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class CollegeAttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeAttendanceApplication.class, args);
	}

	
	@Bean
    public RestTemplate restTemplate() 
	{
        return new RestTemplate();
    }
}
