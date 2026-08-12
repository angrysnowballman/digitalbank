package com.shark_industries.digitalbank.authservice.services;

import com.shark_industries.digitalbank.api.controller.RegistrationRequest;
import com.shark_industries.digitalbank.api.controller.RegistrationResponse;
import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.authservice.model.UserRepository;
import com.shark_industries.digitalbank.exception.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public RegistrationResponse register(RegistrationRequest request){
        if(userRepository.findByUsername(request.username())){
            throw new UserAlreadyExistsException("User with username '%s' already exists")
//                            .formatted(request.username());
        });

        User user = User.builder().username(request.username()).password(bCryptPasswordEncoder.encode(request.password())).build();


        User savedUser = userRepository.save(user);

        return  new RegistrationResponse(savedUser.getUserid(), savedUser.getUsername());
    }

    public String login(String username, String password){
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if(optionalUser.isEmpty()){
            return "Юзер не найден";
        }

        //На кой хер тут это????
        User user = optionalUser.get();

       if(bCryptPasswordEncoder.matches(password, user.getPassword())){
           return  "Логин успешен";
       }
       return "Wrong password";
    }


}
