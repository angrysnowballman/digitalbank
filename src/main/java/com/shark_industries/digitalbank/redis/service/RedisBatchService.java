package com.shark_industries.digitalbank.redis.service;

import com.shark_industries.digitalbank.redis.interfaces.RedisRecord;
import com.shark_industries.digitalbank.redis.refresher.PreRefreshAction;
import jakarta.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RedisBatchService implements PreRefreshAction {

    @Setter
    private int batchSize = 100;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void batchMSet(List<? extends RedisRecord> entities) {
        Iterator<? extends RedisRecord> it = entities.iterator();

        while (it.hasNext()) {
            Map<String, String> map = new HashMap<>(batchSize);
            int counter = 0;

            while (counter < batchSize && it.hasNext()) {
                RedisRecord entity = it.next();
                map.put(entity.getKey(), entity.getValue());
                // правильно??? ************************************************
                counter++;
            }

            redisTemplate.opsForValue().multiSet(map);
        }
    }

    public Map<String, String> batchMGet(List<? extends RedisRecord> keyGenerators) {
        Map<String, String> result = new HashMap<>();

        Iterator<? extends RedisRecord> it = keyGenerators.iterator();

        while (it.hasNext()) {
            List<String> keys = new ArrayList<>(batchSize);

            int counter = 0;

            while (counter < batchSize && it.hasNext()) {
                keys.add(it.next().getKey());
                counter++;
            }

            List<String> values = redisTemplate.opsForValue().multiGet(keys);

            if (values != null) {
                for (int i = 0; i < keys.size(); i++) {
                    String value = values.get(i);
                    if (value != null) {
                        result.put(keys.get(i), value);
                    }
                }
            }
        }

        return result;
    }

    public void batchDelete(List<? extends RedisRecord> entities) {
        Iterator<? extends RedisRecord> it = entities.iterator();

        while (it.hasNext()) {
            List<String> list = new ArrayList<>(batchSize);
            int counter = 0;

            while (counter < batchSize && it.hasNext()) {
                list.add(it.next().getKey());
                counter++;
            }

            redisTemplate.delete(list);
        }
    }

    @Override
    public void flushAll() {
        redisTemplate.getConnectionFactory().getConnection().serverCommands().flushAll();
    }

}