package com.example.demo.security;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class CacheConfig {

	@Bean
	public CaffeineCacheManager cacheManager() {
	    CaffeineCacheManager cacheManager =
	            new CaffeineCacheManager("attendanceCache","allAttendanceCache","allAttendancePostCache");

	    cacheManager.setCaffeine(Caffeine.newBuilder()
	            .initialCapacity(10)
	            .maximumSize(100)
	            .expireAfterWrite(10, TimeUnit.MINUTES));

	    return cacheManager;
	}

}
