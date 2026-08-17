package com.shark_industries.digitalbank.authservice.services;

import com.shark_industries.digitalbank.api.controller.AuthRequest;
import com.shark_industries.digitalbank.api.controller.LoginResponse;
import com.shark_industries.digitalbank.api.controller.RegistrationResponse;
import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.authservice.model.UserRepository;
import com.shark_industries.digitalbank.exception.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private  final PasswordEncoder bCryptPasswordEncoder;
    private  final  JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public RegistrationResponse register(AuthRequest request){
        User user = userRepository.findByFirstname(request.username()).orElse(null);
        if(user != null){
            throw new UserAlreadyExistsException("User with username '%s' already exists".formatted(request.username()));

        }

        user = User.builder().firstname(request.username()).password(bCryptPasswordEncoder.encode(request.password())).build();


        User savedUser = userRepository.save(user);

        return  new RegistrationResponse(savedUser.getUserid(), savedUser.getFirstname());
    }

    //Добавить из контроллера
    public LoginResponse  login(AuthRequest request){
        User user = userRepository.findByFirstname(request.username()).orElse(null);

        if(user == null){
            throw new RuntimeException("Юзер не найден");
        }

       if(!bCryptPasswordEncoder.matches(request.password(), user.getPassword())){
           throw new RuntimeException("Wrong password");
       }

       String token = jwtService.generateToken(user.getFirstname());

       return new LoginResponse(token);

    }

}
