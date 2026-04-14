package com.careerit.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.careerit.dto.ErrorResponse;
import com.careerit.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	
	ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // ✅ Skip public APIs
        if (path.equals("/") ||
            path.equals("/register") ||
            path.equals("/login") ||
            path.equals("/forgot-password") ||
            path.equals("/reset-password")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        	ErrorResponse error = new ErrorResponse("Invalid or Expired Token");
     	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
     	    response.setContentType("application/json");
     	    response.getWriter().write(mapper.writeValueAsString(error));
     	    return;
        }

       
        
        String token = authHeader.substring(7);

        try {
        	
        	if (!jwtUtil.validateToken(token)) {
        	    ErrorResponse error = new ErrorResponse("Invalid or Expired Token");
        	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	    response.setContentType("application/json");
        	    response.getWriter().write(mapper.writeValueAsString(error));
        	    return;
        	}

            // ✅ Extract email
        	String email = jwtUtil.extractEmail(token);
        	String role = jwtUtil.extractRole(token);

            // ✅ Set authentication
        	UsernamePasswordAuthenticationToken authentication =
        	        new UsernamePasswordAuthenticationToken(
        	                email,
        	                null,
        	                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        	        );

        	SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
        	ErrorResponse error = new ErrorResponse("Invalid or Expired Token");
     	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
     	    response.setContentType("application/json");
     	    response.getWriter().write(mapper.writeValueAsString(error));
     	    return;
        }

        filterChain.doFilter(request, response);
    }
}