package com.shark_industries.digitalbank.accountservice.services;

import com.shark_industries.digitalbank.accountservice.model.Account;
import com.shark_industries.digitalbank.accountservice.model.AccountRepository;
import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.accountservice.enums.Currency;

import java.math.BigDecimal;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(User user) {
        if(user == null){
            throw new RuntimeException("User cannot be null");
        }
        Account account = Account.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .balance(BigDecimal.ZERO)
                .currency(Currency.USD)
                .build();

        return accountRepository.save(account);
    }
}

