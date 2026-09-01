package com.erickdc.lacasadelalfajorbackend.controller;

import com.erickdc.lacasadelalfajorbackend.dto.LoginRequest;
import com.erickdc.lacasadelalfajorbackend.dto.LoginResponse;
import com.erickdc.lacasadelalfajorbackend.dto.RegistroRequest;
import com.erickdc.lacasadelalfajorbackend.dto.RegistroResponse;
import com.erickdc.lacasadelalfajorbackend.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    public RegistroResponse registrar(@RequestBody RegistroRequest request) {
        return authService.registrar(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public Long me(Authentication authentication) {
        return (Long) authentication.getPrincipal();
    }
}