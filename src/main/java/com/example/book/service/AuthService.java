package com.example.book.service;

import com.example.book.entity.auth.RegistrationRequest;

public interface AuthService {
    void register(RegistrationRequest registrationRequest);
}
