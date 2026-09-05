package com.shark_industries.digitalbank.redis.refresher;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RefreshManager {

    private final List<RedisRefresh> refreshList;
    private final PreRefreshAction preRefreshAction;

    public RefreshManager(List<RedisRefresh> refreshList, PreRefreshAction preRefreshAction) {
        this.refreshList = refreshList;
        this.preRefreshAction = preRefreshAction;
    }

    public void refreshAllCache() {
        preRefreshAction.flushAll();
        refreshList.forEach(RedisRefresh::refreshCache);
    }
}