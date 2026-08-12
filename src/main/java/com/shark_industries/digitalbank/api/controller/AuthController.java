package com.shark_industries.digitalbank.api.controller;

import com.shark_industries.digitalbank.authservice.services.AuthService;
import jakarta.servlet.Registration;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService = new AuthService();

     public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/api/registration")
    public ResponseEntity<RegistrationResponse> registration(@Valid @RequestBody RegistrationRequest request){
        RegistrationRequest response = authService.register(request);
                return response()
    };

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        return authService.login(username, password);
    }

}


