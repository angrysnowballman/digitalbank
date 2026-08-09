package com.shark_industries.digitalbank.api.controller;
import com.shark_industries.digitalbank.authservice.services.UserService;
import com.shark_industries.digitalbank.api.controller.UserController;
import com.shark_industries.digitalbank.authservice.services.AuthService;
import com.shark_industries.digitalbank.authservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    private AuthService authService;

    @GetMapping("/user")
    public User getUser(){
        return userService.getUser(getUser().getUserid());
    }
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {
        boolean authenticated =
                authService.login(username, password);

        if(authenticated){
            return "Авторизация успешна";
        }
        return "Неверный username или password";
    };

    @PostMapping("/registration")
    public User registrUser(@RequestParam String username,
                              @RequestParam String password) {
        return authService.register(username, password);
    }


}
