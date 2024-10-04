package com.example.GenerateJsonWebToken.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests)->
                requests
                        .requestMatchers("/api/authentication/**")
                        .permitAll()
                        .requestMatchers("/api/v2/demo").hasRole("USER")
                        .requestMatchers("/api/v2/getAll").hasRole("ADMIN")
                        .requestMatchers("/api/v2/delete/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/v2/getByName/{name}").hasRole("ADMIN")
                        .requestMatchers("/api/v2/update/{id}").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated());
        http.authenticationProvider(authProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }



}
