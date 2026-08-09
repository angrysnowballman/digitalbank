package com.shark_industries.digitalbank.authservice.services;

import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.authservice.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

    public String encode(String userpassword) {
        return encoder.encode(userpassword);

    }
    //подсмотрел
    public boolean login(String login, String password){
        Optional<User> userOptioanl = userRepository.findByUsername(login);

        if (userOptioanl.isEmpty()){
            return false;
        }

        User user = userOptioanl.get();
        //matches
        return encoder.matches(password, user.getPassword());
    }


}
