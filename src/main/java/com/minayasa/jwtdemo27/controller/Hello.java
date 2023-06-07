package com.minayasa.jwtdemo27.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {

    @PreAuthorize("hasRole('ADMIN')") // @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public String hello() {
        return "Hello";
    }
}
