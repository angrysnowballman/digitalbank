package com.shark_industries.digitalbank.redis.controller;

import com.shark_industries.digitalbank.redis.interfaces.RedisRecord;
import com.shark_industries.digitalbank.redis.refresher.RefreshManager;
import com.shark_industries.digitalbank.redis.service.RedisBatchService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedisControllerTest {
    @LocalServerPort
    private int port;

    private MockMvc mockMvc;

    @Mock
    private RedisBatchService redisBatchService;

    @InjectMocks
    private RedisController redisController;

    private List<RedisRecord> testEntities;

    private RefreshManager refreshManager;



}
