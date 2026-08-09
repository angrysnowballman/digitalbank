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
        Optional<User> userOptional = userRepository.findByUsername(login);

        if (userOptional.isEmpty()){
            return false;
        }

        User user = userOptional.get();
        //matches
        return encoder.matches(password, user.getPassword());
    }

    public User register(String login, String password){
        User user = new User();
        if((!login.isEmpty()) && (!password.isEmpty())){


            user.setUsername(login);
            user.setPassword(encode(password));


        }
        return  userRepository.save(user);
    }


}
