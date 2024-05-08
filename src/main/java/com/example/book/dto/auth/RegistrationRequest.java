package com.example.book.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotEmpty(message = "Firstname cannot be empty")
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;
    @NotEmpty(message = "Lastname cannot be empty")
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @NotEmpty(message = "Email cannot be empty")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Wrong format for the email")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 20, message = "Password should be between 8 and 20 characters long")
    private String password;
}
