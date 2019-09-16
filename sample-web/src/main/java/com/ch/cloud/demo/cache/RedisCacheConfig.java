package com.ch.cloud.demo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author yjph83
 */
@Configuration
public class RedisCacheConfig {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        MyBatisRedisCache.setRedisTemplate(redisTemplate);
    }
}
