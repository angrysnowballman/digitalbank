package com.shark_industries.digitalbank.accountservice.services;

import com.shark_industries.digitalbank.accountservice.model.Account;
import com.shark_industries.digitalbank.accountservice.model.AccountRepository;
import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.accountservice.enums.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
@Service
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

        return saveAccount(account);
    }

//    public String transfer(Long fromId, Long toId, BigDecimal amount) {
//        Account fromUser = accountRepository.findById(fromId).orElse(null);
//        if(fromUser==null){
//            return "fromId user not found";
//        }
//        Account toUser = accountRepository.findById(toId).orElse(null);
//        if(toUser==null){
//            return "UserClient not found";
//        }
//
//        if(fromUser.getBalance().compareTo(amount) >= 0) {
//            toUser.setBalance(toUser.getBalance().add(amount));
//            fromUser.setBalance(fromUser.getBalance().subtract(amount));
//            //подсмотрел
//            accountRepository.save(fromUser);
//            accountRepository.save(toUser);
//        }
//        return (toUser.getAccountId() + " " + toUser.getFirstname() + " пополнил счет на " + toUser.getBalance());
//    }

    public Account getAccountById(Long id) {
        if (id == null) {
            return null;
        }
        return accountRepository.findById(id).orElse(null);
    }

    public Account saveAccount(Account account) {
        if (account != null) {
            return accountRepository.save(account);
        }
        return null;
    }

    public Account getAccountByUUID(UUID uuid) {
        if (uuid  == null) {
            return null;
        }
        return accountRepository.findByUuid(uuid).orElse(null);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


}

