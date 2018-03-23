package com.ch.cloud.demo.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liwei
 */
public class MyBatisRedisCache implements Cache {

    @Autowired
    private static RedisTemplate<String, byte[]> redisTemplate;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;

    public MyBatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    /**
     * TODO Review this is UNUSED
     *
     * @param callback
     * @return
     */
    private Object execute(RedisCallback callback) {
        return callback.doWithRedis(redisTemplate);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        return (Integer) execute(new RedisCallback() {
            @Override
            public Object doWithRedis(RedisTemplate redisTemplate) {

                Map result = redisTemplate.boundHashOps(id).entries();
                return result.size();
            }
        });
    }

    @Override
    public void putObject(final Object key, final Object value) {
        execute(new RedisCallback() {
            @Override
            public Object doWithRedis(RedisTemplate redisTemplate) {
                redisTemplate.boundHashOps(id).put(key.toString(), value);
                return null;
            }
        });
    }

    @Override
    public Object getObject(final Object key) {
        return execute(new RedisCallback() {
            @Override
            public Object doWithRedis(RedisTemplate redisTemplate) {
                return redisTemplate.boundHashOps(id).get(key.toString());
            }
        });
    }

    @Override
    public Object removeObject(final Object key) {
        return execute(new RedisCallback() {
            @Override
            public Object doWithRedis(RedisTemplate redisTemplate) {
                return redisTemplate.boundHashOps(id).delete(key.toString());
            }
        });
    }

    @Override
    public void clear() {
        execute(new RedisCallback() {
            @Override
            public Object doWithRedis(RedisTemplate redisTemplate) {
                redisTemplate.opsForValue().getOperations().delete(id);
                return null;
            }
        });

    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    @Override
    public String toString() {
        return "Redis {" + id + "}";
    }

    public static void setRedisTemplate(RedisTemplate<String, byte[]> redisTemplate) {
        MyBatisRedisCache.redisTemplate = redisTemplate;
    }
}
