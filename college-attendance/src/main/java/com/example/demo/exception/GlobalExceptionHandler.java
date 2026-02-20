package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler 
{

	 @ExceptionHandler(ExpiredJwtException.class)
	 public ResponseEntity<Map<String, Object>> handleExpiredJwt(ExpiredJwtException ex) 
	 {

	        Map<String, Object> error = new HashMap<>();
	        error.put("status", 401);
	        error.put("message", "JWT token has expired. Please login again.");
	        error.put("timestamp", LocalDateTime.now());

	        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	 }

	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) 
	 {

	        Map<String, Object> error = new HashMap<>();
	        error.put("status", 500);
	        error.put("message", ex.getMessage());
	        error.put("timestamp", LocalDateTime.now());

	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
}
