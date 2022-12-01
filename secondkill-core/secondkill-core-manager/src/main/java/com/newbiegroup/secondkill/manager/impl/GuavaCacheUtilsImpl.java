package com.newbiegroup.secondkill.manager.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/11/1 19:45
 */
@Slf4j
@Component
public class GuavaCacheUtilsImpl {

    private Cache<String, Object> localCache = null;

    @PostConstruct
    private void init() {
        localCache = CacheBuilder.newBuilder()
                .initialCapacity(100)
                .maximumSize(100)
                .expireAfterAccess(60, TimeUnit.SECONDS).build();
    }

    public boolean set(String key, Object value) {
        try {
            localCache.put(key, value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Object get(String key) {
        return localCache.getIfPresent(key);
    }

}
