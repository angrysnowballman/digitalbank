package com.shark_industries.digitalbank.api.controller;
import com.shark_industries.digitalbank.authservice.services.UserService;
import com.shark_industries.digitalbank.api.controller.UserController;
import com.shark_industries.digitalbank.authservice.model.UserRepository;
import com.shark_industries.digitalbank.authservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User getUser(){
        return userService.getUser();
    }
}
