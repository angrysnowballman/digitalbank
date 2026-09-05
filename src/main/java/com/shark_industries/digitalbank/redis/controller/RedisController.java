package com.shark_industries.digitalbank.redis.controller;

import com.shark_industries.digitalbank.redis.interfaces.RedisRecord;
import com.shark_industries.digitalbank.redis.refresher.RefreshManager;
import com.shark_industries.digitalbank.redis.service.RedisBatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RefreshManager refreshManager;
    private final RedisBatchService redisBatchService;

    public RedisController(RefreshManager refreshManager, RedisBatchService redisBatchService) {
        this.refreshManager = refreshManager;
        this.redisBatchService = redisBatchService;
    }




    //ID
    @PostMapping("/delete/{batchId}")
    public ResponseEntity<String> deleteteBatch(@RequestBody List<? extends RedisRecord> entities) { // @PathVariable("batchId")
        redisBatchService.batchDelete(entities);
        return ResponseEntity.ok("Deleted");

    }

    @PostMapping("/get")
    public ResponseEntity<Map<String, String>> getBatch(@RequestBody List<? extends RedisRecord> entities) {
        Map<String, String> result = redisBatchService.batchMGet(entities);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/set")
    public ResponseEntity<String> setBatch(@RequestBody List<? extends RedisRecord> entities) {
        redisBatchService.batchMSet(entities);
        return ResponseEntity.ok("Batch set completed");
    }



}
