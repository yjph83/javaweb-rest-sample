package com.ch.cloud.demo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author liwei
 */
@Configuration
public class RedisCacheConfig {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        MyBatisRedisCache.setRedisTemplate(redisTemplate);
    }
}
