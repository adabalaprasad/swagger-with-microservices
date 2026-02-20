package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;



import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter,Ordered
{

	 @Override
	 public int getOrder() 
	 {
	    return -1;
	 }
	 
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        
        //  Read Authorization header
        String authHeader = exchange.getRequest()
                                    .getHeaders()
                                    .getFirst(HttpHeaders.AUTHORIZATION);
        
        
        System.out.println("Request Path: " + path);
        System.out.println("Authorization Header: " + authHeader);
        System.out.println("Authorization Header: " + exchange.getRequest().getHeaders().getFirst("Authorization"));

        if (path.startsWith("/auth/")) {
            return chain.filter(exchange);  // ✅ add this block
        }
        
        if (path.contains("/v3/api-docs") ||
        		path.equals("/attendance/login") ||
        	    path.contains("/swagger-ui") ||
        	    path.contains("/webjars")) 
        {
           return chain.filter(exchange);
        }

       

        if (authHeader == null || !authHeader.startsWith("Bearer ")) 
        {
        	 return unauthorized(exchange, "Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        try {
            //  Validate token
            String email = JwtUtil.extractEmail(token);

            //  Forward headers to downstream services
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(exchange.getRequest().mutate()
                            .header(HttpHeaders.AUTHORIZATION, authHeader) // 🔑 MOST IMPORTANT
                            .header("X-User-Email", email)
                            .build())
                    .build();

            return chain.filter(modifiedExchange);

        } catch (ExpiredJwtException e) {
            return unauthorized(exchange, "JWT token expired");
        } catch (Exception e) {
            return unauthorized(exchange, "Invalid JWT token");
        }
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String msg) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        return exchange.getResponse().writeWith(
                Mono.just(exchange.getResponse()
                        .bufferFactory().wrap(bytes)));
    }
    
}
