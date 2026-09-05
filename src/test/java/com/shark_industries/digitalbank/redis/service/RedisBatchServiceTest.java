package com.shark_industries.digitalbank.redis.service;

import com.shark_industries.digitalbank.redis.interfaces.RedisRecord;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Tag("integration")
class RedisBatchServiceTest {

    @Autowired
    private RedisBatchService redisBatchService;

     @Test
    void test() {
        List<TestBatch> testBatches = List.of(
                new TestBatch(1L, "Test 1"),
                new TestBatch(2L, "Test 1")
        );

        redisBatchService.batchMSet(testBatches);

        Map<String, String> result = redisBatchService.batchMGet(testBatches);
        System.out.println(result);
        assertTrue(result.containsKey(testBatches.get(0).getKey()));
        assertTrue(result.containsKey(testBatches.get(1).getKey()));

        redisBatchService.batchDelete(testBatches);

        var result2 = redisBatchService.batchMGet(testBatches);
        System.out.println(result2);
        assertEquals(0, result2.size());
    }

    private class TestBatch implements RedisRecord {
        private Long id;
        private String description;
        private String key;
        private String value;

        public TestBatch(Long id, String description) {
            this.id = id;
            this.key = UUID.randomUUID().toString();
            this.description = description;
        }

        @Override
        public String getKey() {
            return "TestBatch:" + id + ":" + key;
        }

        @Override
        public String getValue() {

            return "TestBatch{" +
                    "id=" + id +
                    ", description='" + description + '\'' +
                    ", key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
