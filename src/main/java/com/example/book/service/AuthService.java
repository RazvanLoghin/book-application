package com.example.book.service;

import com.example.book.entity.auth.AuthenticationResponse;
import com.example.book.entity.auth.AuthenticationRequest;
import com.example.book.entity.auth.RegistrationRequest;
import jakarta.mail.MessagingException;

public interface AuthService {
    void register(RegistrationRequest registrationRequest) throws MessagingException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void enableAccount(String token) throws MessagingException;
}
