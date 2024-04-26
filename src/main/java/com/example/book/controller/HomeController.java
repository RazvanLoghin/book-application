package com.example.book.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Home Controller")
@RequestMapping("home")
public class HomeController {

    @GetMapping
    public String getHomePage() {
        return "This is the home page";
    }
}
