package com.shark_industries.digitalbank.accountservice.controller;

import com.shark_industries.digitalbank.accountservice.model.Account;
import com.shark_industries.digitalbank.accountservice.model.AccountRepository;
import com.shark_industries.digitalbank.accountservice.services.AccountService;
import com.shark_industries.digitalbank.authservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(User user) {
        Account account = accountService.createAccount(user);
        return ResponseEntity.ok(account);
    }
}
