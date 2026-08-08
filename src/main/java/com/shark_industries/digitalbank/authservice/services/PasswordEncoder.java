package com.shark_industries.digitalbank.authservice.services;

import com.shark_industries.digitalbank.authservice.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

    public String encode(String userpassword) {
        return encoder.encode(userpassword);

    }
}
