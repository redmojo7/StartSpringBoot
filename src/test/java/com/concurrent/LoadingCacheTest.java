package com.concurrent;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * LoadingCacheTest
 *
 * @author : Donald Cai
 * @date :   2018-02-01.
 * @refer： http://blog.csdn.net/albertfly/article/details/51971086
 */
public class LoadingCacheTest {

    private static LoadingCache<String, String> loadingCache =
            CacheBuilder.newBuilder()
                    .concurrencyLevel(10)   //设置并发级别为10，并发级别是指可以同时写缓存的线程数
                    .expireAfterWrite(8, TimeUnit.SECONDS)
                    .initialCapacity(10)    //设置缓存容器的初始容量为10
                    .maximumSize(100)       //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                    .build(new CacheLoader<String, String>() {
                        @Override
                        public String load(String key) throws Exception {
                            return null;
                        }
                    });

    public static void main(String[] args) {
        String value = "";
        loadingCache.put("a", "at");
        try {
             value = loadingCache.get("a");
        } catch (ExecutionException e) {
            System.out.printf("loadingCache.a Error " + e.getMessage());
        }
        System.out.printf("loadingCache.a = " + value);
    }

    public void test() {
        boolean expired = Boolean.TRUE;
        final String login = "as@as.com";
        String result= "";
        try {
            if (expired) {
                /*
                 *  old RefreshToken is expired
                 */
                result = loadingCache.get(login);
                if (Objects.isNull(result)) {
                    /*
                     * create a new RefreshToken
                     */
                    result = UUID.randomUUID().toString();
                    System.out.printf("create a new RefreshToken, value = " + result);
                    loadingCache.put(login, result);
                }
            }

        } catch (ExecutionException e) {
            System.out.printf("loadingCache get failed for " + login + e.getMessage());
        }
        System.out.printf("loadingCache.a = " + result);
    }



}
