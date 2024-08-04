package com.example.GenerateJsonWebToken.authController;

import com.example.GenerateJsonWebToken.Config.JwtService;
import com.example.GenerateJsonWebToken.Details.Employees;
import com.example.GenerateJsonWebToken.Details.Role;
import com.example.GenerateJsonWebToken.RepositoryPackage.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService service;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request)
    {
        var user= Employees.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        String token=service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(),request.getPassword())
        );
        var user=userRepo.findByName(request.getName())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));

        userRepo.save(user);
        String token=service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
