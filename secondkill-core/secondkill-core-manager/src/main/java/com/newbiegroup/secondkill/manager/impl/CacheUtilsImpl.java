package com.newbiegroup.secondkill.manager.impl;

import com.alibaba.fastjson.JSON;
import com.newbiegroup.secondkill.manager.CacheManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/7/10 15:01
 */
@Slf4j
@Component
public class CacheUtilsImpl implements CacheManager {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean set(String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception ex) {
            log.error("springDataRedis-->set failure key:" + key + ",value:" + value, ex);
        }
        return false;
    }

    @Override
    public boolean set(String key, Object value) {
        if (StringUtils.isBlank(key) || null == value) {
            return false;
        }
        try {
            return set(key, JSON.toJSONString(value));
        } catch (Exception ex) {
            log.error("springDataRedis-->set Object failure key:" + key + ",value:" + value, ex);
        }
        return false;
    }

    @Override
    public boolean set(String key, Object value, Long time, TimeUnit unit) {
        if (StringUtils.isBlank(key) || null == value) {
            return false;
        }
        try {
            boolean result = set(key, JSON.toJSONString(value));
            expire(key, time, unit);
            return result;
        } catch (Exception ex) {
            log.error("springDataRedis-->set Object failure key:" + key + ",value:" + value, ex);
        }
        return false;
    }

    @Override
    public String get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception ex) {
            log.error("springDataRedis-->get failure key:" + key, ex);
        }
        return null;
    }

    public <T> T get(String key, Class<T> clazz) {
        if (!StringUtils.isBlank(key) && null != clazz) {
            String stringValue = this.get(key);
            if (StringUtils.isBlank(stringValue)) {
                return null;
            } else {
                try {
                    return JSON.parseObject(stringValue, clazz);
                } catch (Exception var5) {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean setEx(String key, String value, long seconds) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value) || 0 > seconds) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception ex) {
            log.error("springDataRedis-->setEx failure key:" + key + ",value:" + value + ",seconds:" + seconds, ex);
        }
        return false;
    }

    @Override
    public boolean setEx(String key, String value, long time, TimeUnit unit) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value) || 0 > time) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value, time, unit);
            return true;
        } catch (Exception ex) {
            log.error("springDataRedis-->setEx failure key:" + key + ",value:" + value + ",timeUnit:" + unit + ",time:" + time, ex);
        }
        return false;
    }

    @Override
    public boolean setEx(String key, Object value, long seconds) {
        if (StringUtils.isBlank(key) || null == value || 0 > seconds) {
            return false;
        }
        try {
            return setEx(key, JSON.toJSONString(value), seconds);
        } catch (Exception ex) {
            log.error("springDataRedis-->setEx Object failure key:" + key + ",value:" + value + ",seconds:" + seconds,
                    ex);
        }
        return false;
    }

    @Override
    public boolean setNx(String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return false;
        }
        try {
            return redisTemplate.opsForValue().setIfAbsent(key, value);
        } catch (Exception ex) {
            log.error("springDataRedis-->setNX failure key:" + key + ",value:" + value, ex);
        }
        return false;
    }

    @Override
    public boolean setNx(String key, Object value) {
        if (StringUtils.isBlank(key) || null == value) {
            return false;
        }
        try {
            return setNx(key, JSON.toJSONString(value));
        } catch (Exception ex) {
            log.error("springDataRedis-->setNX Object failure key:" + key + ",value:" + value, ex);
        }
        return false;
    }

    @Override
    public boolean hSet(String key, String field, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(field) || StringUtils.isBlank(value)) {
            return false;
        }
        try {
            redisTemplate.opsForHash().put(key, field, value);
            return true;
        } catch (Exception ex) {
            log.error("springDataRedis-->hSet failure key:" + key + ",field:" + field + ",value:" + value, ex);
        }

        return false;
    }

    @Override
    public Long incr(String key,Integer amount) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return redisTemplate.opsForValue().increment(key, amount);
        } catch (Exception ex) {
            log.error("springDataRedis--> incr failure key:" + key, ex);
        }
        return null;
    }

    @Override
    public boolean expire(String key, long seconds) {
        if (StringUtils.isBlank(key) || 0 > seconds) {
            return false;
        }
        try {
            return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        } catch (Exception ex) {
            log.error("springDataRedis--> expire failure key:" + key + ",seconds:" + seconds, ex);
        }
        return false;
    }
    public boolean expire(String key, long time,TimeUnit unit){
        if (StringUtils.isBlank(key) || 0 > time) {
            return false;
        }
        try {
            return redisTemplate.expire(key, time, unit);
        } catch (Exception ex) {
            log.error("springDataRedis--> expire failure key:" + key + ",time:" + time, ex);
        }
        return false;
    }

    @Override
    public boolean del(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            return redisTemplate.delete(key);
        } catch (Exception ex) {
            log.error("springDataRedis--> incr failure key:" + key, ex);
        }
        return false;
    }

    @Override
    public Long increment(String key, long amount) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return redisTemplate.opsForValue().increment(key, amount);
        } catch (Exception ex) {
            log.error("springDataRedis--> incr failure key:" + key, ex);
        }
        return null;
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
