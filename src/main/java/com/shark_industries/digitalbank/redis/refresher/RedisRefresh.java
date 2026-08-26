package com.shark_industries.digitalbank.redis.refresher;

import com.shark_industries.digitalbank.redis.interfaces.RedisRecord;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface RedisRefresh {
    void refresh() throws Exception;


}
