package com.docbook.springsecurityconfigurations;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain sfc(HttpSecurity http) throws Exception{

        http.csrf().disable().cors().disable();

        http.authorizeHttpRequests().anyRequest().permitAll();

        return http.build();
    }
}
