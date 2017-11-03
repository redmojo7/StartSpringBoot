package com.strat.springboot.controller.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;

/**
 * @author : Donald
 * @date : 2017/11/3 17:59.
 * @description :
 */
public class JuavaCacheUtil {
    
    private static LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
        .maximumSize(1000)    // 最多可以缓存1000个key
        .expireAfterWrite(10, TimeUnit.MINUTES)  // 过期时间
        .build(
            new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String key) {
                    return compute(key);
                }
            });
    
    private static Integer compute(String name) {
        // 一些列复杂的计算过程
        return null;
    }
}
