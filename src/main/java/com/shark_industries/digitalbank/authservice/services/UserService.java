package com.shark_industries.digitalbank.authservice.services;

import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.authservice.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User getUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return null;
        }
        return user;
    };




}
