package com.strat.springboot.controller.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.strat.springboot.controller.dto.UserClientInfo;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Donald
 * @date : 2018/2/2 9:50.
 * @description :
 */
public enum LoginLoadingCache {
    
    INSTANCE;
    
    private static Logger log = LoggerFactory.getLogger(LoginLoadingCache.class);
    
    private static LoadingCache<String, ReentrantLock> loadingCache = CacheBuilder.newBuilder()
        .concurrencyLevel(10)
        .expireAfterWrite(8, TimeUnit.SECONDS)
        .initialCapacity(10)
        .maximumSize(100)
        .removalListener(createRemovalListener())
        .build(createCacheLoad());
    
    private static CacheLoader<String, ReentrantLock> createCacheLoad() {
        return new CacheLoader<String, ReentrantLock>() {
            @Override
            public ReentrantLock load(String key) throws Exception {
                return null;
            }
        };
    }
    
    private static RemovalListener<Object, Object> createRemovalListener() {
        return notification -> {
            String key = notification.getKey().toString();
            String[] userClientInfoStr = key.split("_");
            UserClientInfo userClientInfo = new UserClientInfo(userClientInfoStr[0], userClientInfoStr[1]);
            log.debug("{} was removed, cause is {}.", notification.getKey(), notification.getCause());
        };
    }
    
    
    public ReentrantLock get(UserClientInfo userClientInfo) {
        ReentrantLock result = null;
        try {
            result = loadingCache.get(userClientInfo.toString());
            log.debug("[LoginLoadingCache] [get] result = [ {} ], for [ {} ].", result, userClientInfo.toString());
            
        } catch (ExecutionException e) {
            log.debug("Get result failed,error : {}.", e.getMessage());
        }
        return result;
    }
    
    public void put(UserClientInfo userClientInfo, ReentrantLock result) {
        loadingCache.put(userClientInfo.toString(), result);
        log.debug("[LoginLoadingCache] [put] result = [ {} ] for [ {} ].", result, userClientInfo.toString());
    }
    
}
