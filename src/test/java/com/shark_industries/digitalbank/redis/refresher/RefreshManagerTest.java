package com.shark_industries.digitalbank.redis.refresher;

import com.shark_industries.digitalbank.accountservice.model.Account;
import com.shark_industries.digitalbank.accountservice.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RefreshManagerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    private RefreshManager refreshManager;

    private Account testAccount;



    @BeforeEach
    void setUp() {
        testAccount = getTestAccountUser();
        refreshManager = new RefreshManager(accountService, redisTemplate);
    }



    //ничего не понимаю, Бузова.jpg
    @Test
    void testRefresh() {
        Set<String> oldkeys = Set.of("account:1", "account:2");
        when(redisTemplate.keys("account:*")).thenReturn(oldkeys);
//        when(accountService.getAllAccounts()).thenReturn(List.of(testAccount));
//        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        refreshManager.refresh();

        verify(redisTemplate).delete(oldkeys);
//        verify(valueOperations).set("account:*", testAccount);

    }

    private  Account getTestAccountUser() {
        return Account.builder()
                .accountId(1L)
                .uuid(UUID.randomUUID())
                .firstname("Ivan")
                .lastname("Ivanov")
                .balance(BigDecimal.valueOf(1000))
                .build();
    }
}