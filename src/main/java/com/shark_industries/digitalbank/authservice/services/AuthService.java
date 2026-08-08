package com.shark_industries.digitalbank.authservice.services;

import org.springframework.security.access.annotation.Secured;

public class AuthService {
    @Secured("ROLE_ADMIN")
    public void test() {

    }
}
