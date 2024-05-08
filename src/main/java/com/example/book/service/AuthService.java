package com.example.book.service;

import com.example.book.dto.auth.AuthenticationResponse;
import com.example.book.dto.auth.AuthenticationRequest;
import com.example.book.dto.auth.RegistrationRequest;
import jakarta.mail.MessagingException;

public interface AuthService {
    void register(RegistrationRequest registrationRequest) throws MessagingException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void enableAccount(String token) throws MessagingException;
}
