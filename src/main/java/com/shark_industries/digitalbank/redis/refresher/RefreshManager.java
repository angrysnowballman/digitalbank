package com.shark_industries.digitalbank.redis.refresher;


import com.shark_industries.digitalbank.accountservice.model.Account;
import com.shark_industries.digitalbank.accountservice.services.AccountService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class RefreshManager implements RedisRefresh{


    private final AccountService accountService;
    private final RedisTemplate<String, Object> redisTemplate;

    public RefreshManager(AccountService accountService, RedisTemplate<String, Object> redisTemplate) {
        this.accountService = accountService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void refresh(){
        Set<String> keys = redisTemplate.keys("account:*");

        if (keys != null) {
            redisTemplate.delete(keys);
        }

        List<Account> accounts = accountService.getAllAccounts();

        for (Account account : accounts) {
            String key = "account:" + account.getAccountId();
            //что это вообще
            redisTemplate.opsForValue().set(key, account);
        }

    }
}
