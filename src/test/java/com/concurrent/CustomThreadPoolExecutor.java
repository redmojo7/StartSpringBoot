package com.concurrent;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *    方法中建立一个核心线程数为30个，缓冲队列有10个的线程池。每个线程任务，执行时会先睡眠3秒，保证提交10任务时，
 * 线程数目被占用完，再提交30任务时，阻塞队列被占用完，，这样提交第41个任务是，会交给CustomRejectedExecutionHandler
 * 异常处理类来处理。
 * @author : Donald
 * @date : 2017/10/31 14:58.
 * @description :
 */
public class CustomThreadPoolExecutor {

    private static LoadingCache<String, String> loadingCache =
            CacheBuilder.newBuilder()
                    .concurrencyLevel(10)   //设置并发级别为10，并发级别是指可以同时写缓存的线程数
                    .expireAfterWrite(8, TimeUnit.SECONDS)
                    .initialCapacity(10)    //设置缓存容器的初始容量为10
                    .maximumSize(100)       //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                    .removalListener(new RemovalListener<Object, Object>() {
                        @Override
                        public void onRemoval(RemovalNotification<Object, Object> notification) {
                            System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                        }
                    })
                    .build(new CacheLoader<String, String>() {
                        @Override
                        public String load(String key) throws Exception {
                            return "";
                        }
                    });

    private ThreadPoolExecutor pool = null;

    private static final ReentrantLock lock = new ReentrantLock();
    
    
    /**
     * 线程池初始化方法
     *
     * corePoolSize 核心线程池大小----10
     * maximumPoolSize 最大线程池大小----30
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     *                          即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     *                                任务会交给RejectedExecutionHandler来处理
     */
    public void init() {
        pool = new ThreadPoolExecutor(
            10,
            30,
            30,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue<Runnable>(10),
            new CustomThreadFactory(),
            new CustomRejectedExecutionHandler());
    }
    
    
    public void destory() {
        if(pool != null) {
            pool.shutdownNow();
        }
    }
    
    
    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }
    
    private class CustomThreadFactory implements ThreadFactory {
        
        private AtomicInteger count = new AtomicInteger(0);
        
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = CustomThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
            System.out.println(threadName);
            t.setName(threadName);
            return t;
        }
    }
    
    
    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 记录异常
            // 报警处理等
            System.out.println("error.............");
        }
    }
    
    
    
    // 测试构造的线程池
    public static void main(String[] args) {
        CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
        // 1.初始化
        exec.init();
        
        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        for(int i=1; i<100; i++) {
            System.out.println("提交第" + i + "个任务!");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3);
//                        test();
//                        fetchTokenPlanOne();
                        fetchTokenPlanTwo();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("running=====");
                }
            });
        }
        
        
        
        // 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
        // exec.destory();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The last one is :");
        fetchTokenPlanTwo();
    }

    public static void test() {
        boolean expired = Boolean.TRUE;
        final String login = "as@as.com";
        String result= "";
        try {
            if (expired) {
                /*
                 *  old RefreshToken is expired
                 */
                synchronized (CustomThreadPoolExecutor.class) {
                    if (StringUtils.isBlank(result)) {
                    /*
                     * create a new RefreshToken
                     */
                        result = UUID.randomUUID().toString();
                        System.out.println("create a new RefreshToken, value = " + result);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("loadingCache get failed for " + login + e.getMessage());
        }
        System.out.println("loadingCache.result = " + result);
    }

    public static void fetchTokenPlanOne() {
        boolean expired = Boolean.TRUE;
        final String login = "as@as.com";
        String result= "";
        try {
            if (expired) {
                /*
                 *  old RefreshToken is expired
                 */
                synchronized (CustomThreadPoolExecutor.class) {
                    result = loadingCache.get(login);
                    if (StringUtils.isBlank(result)) {
                    /*
                     * create a new RefreshToken
                     */
                        result = UUID.randomUUID().toString();
                        System.out.println("create a new RefreshToken, value = " + result);
                        loadingCache.put(login, result);
                    }
                }
            }

        } catch (ExecutionException e) {
            System.out.println("loadingCache get failed for " + login + e.getMessage());
        }
        System.out.println("loadingCache.result = " + result);
    }

    public static void fetchTokenPlanTwo() {
        boolean expired = Boolean.TRUE;
        final String login = "as@as.com";
        String result= "";
        try {
            if (expired) {
                /*
                 *  old RefreshToken is expired
                 */
                lock.lock();
                try {
                    result = loadingCache.get(login);
                    if (StringUtils.isBlank(result)) {
                    /*
                     * create a new RefreshToken
                     */
                        result = UUID.randomUUID().toString();
                        System.out.println("create a new RefreshToken, value = " + result);
                        loadingCache.put(login, result);
                    }
                } finally {
                    lock.unlock();
                }
            }

        } catch (ExecutionException e) {
            System.out.println("loadingCache get failed for " + login + e.getMessage());
        }
        System.out.println("loadingCache.result = " + result);
    }
}
