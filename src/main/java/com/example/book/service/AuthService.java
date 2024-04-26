package com.example.book.service;

import com.example.book.entity.auth.AuthenticateResponse;
import com.example.book.entity.auth.AuthenticationRequest;
import com.example.book.entity.auth.RegistrationRequest;
import jakarta.mail.MessagingException;

public interface AuthService {
    void register(RegistrationRequest registrationRequest) throws MessagingException;

    AuthenticateResponse authenticate(AuthenticationRequest request);

    void enableAccount(String token) throws MessagingException;
}
