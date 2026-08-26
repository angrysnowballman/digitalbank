package com.shark_industries.digitalbank.redis.service;

import com.shark_industries.digitalbank.redis.interfaces.RedisRecord;
import jakarta.validation.Valid;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RedisBatchService {
    private final int batchSize = 100;
    private final RedisTemplate<String, String> redisTemplate;

    public RedisBatchService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void batchMSet(List<? extends RedisRecord> entities) {
        Iterator<? extends RedisRecord> it = entities.iterator();
        while (it.hasNext()) {
            Map<String, String> map = new HashMap<>(batchSize);

            int count = 0;
            while (it.hasNext() && count < batchSize) {
                RedisRecord entity = it.next();
                map.put(entity.getKey(), entity.getValue());
                count++;
            }

            redisTemplate.opsForValue().multiSet(map);
        }
    }

    public Map<String, String> batchMGet(List<? extends RedisRecord> entities) {
        Map<String, String> result = new HashMap<>();

        Iterator<? extends RedisRecord> it = entities.iterator();
        while (it.hasNext()) {
            List<String> keys = new ArrayList<>(batchSize);

            int count = 0;
            while (it.hasNext() && count < batchSize) {
                keys.add(it.next().getKey());
                count++;
            }

            var values = redisTemplate.opsForValue().multiGet(keys);
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
    // TODO : flushAll через интерфейс, ButchDelete, интерфейс refresh -> Refreshmanager, Tests
    public void deleteBatch(List<? extends RedisRecord> entities) {
        Iterator<? extends RedisRecord> it = entities.iterator();
        while (it.hasNext()) {
            Map<String, String> map = new HashMap<>(batchSize);

            int count = 0;
            while (it.hasNext() && count < batchSize) {
                RedisRecord entity = it.next();
                map.remove(entity.getKey(), entity.getValue());
                count++;
            }

            redisTemplate.opsForValue().multiSet(map);
        }
    }





}
