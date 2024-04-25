package com.example.book.service.impl;

import com.example.book.entity.User;
import com.example.book.entity.auth.RegistrationRequest;
import com.example.book.entity.token.Token;
import com.example.book.exception.RoleNotFoundException;
import com.example.book.repository.RoleRepository;
import com.example.book.repository.TokenRepository;
import com.example.book.repository.UserRepository;
import com.example.book.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public void register(RegistrationRequest registrationRequest) {
        var userRole = roleRepository.findByName("USER").orElseThrow(() ->
                new RoleNotFoundException("Role USER was not initialized"));
        var user = User.builder()
                .firstname(registrationRequest.getFirstname())
                .lastname(registrationRequest.getLastname())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .roles(List.of(userRole))
                .accountLocked(false)
                .enabled(false)
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) {
        var newToken = generateAndSaveActivationToken(user);

    }

    private String generateAndSaveActivationToken(User user) {
        String generateToken = generateToken();
        var token = Token.builder()
                .token(generateToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generateToken;
    }

    private String generateToken() {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for(int i = 0; i < 8; i++) {
            codeBuilder.append(random.nextInt(characters.length()));
        }
        return codeBuilder.toString();
    }
}
