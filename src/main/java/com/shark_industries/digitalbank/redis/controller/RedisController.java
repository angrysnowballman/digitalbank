package com.shark_industries.digitalbank.redis.controller;

import com.shark_industries.digitalbank.redis.refresher.RefreshManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RefreshManager refreshManager;

    public RedisController(RefreshManager refreshManager) {
        this.refreshManager = refreshManager;
    }

    @Scheduled(fixedRate = 60000) // каждые 60 секунд
    public void scheduledRefresh() {
        refreshManager.refresh();
    }

}
