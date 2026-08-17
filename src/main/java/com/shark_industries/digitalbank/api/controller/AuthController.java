package com.shark_industries.digitalbank.api.controller;

import com.shark_industries.digitalbank.authservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shark_industries.digitalbank.api.controller.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //Запомнить создавать без присваивания
    private final AuthService authService;

     public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody AuthRequest request){
        RegistrationResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    };

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest request){
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

}


