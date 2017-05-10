package com.example;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * MyOkHttpTest
 *
 * @author : Donald Cai
 * @date :   2017/5/6.
 */
@Service
@Transactional
public class MyOkHttpTest {

    private static final Logger log = LoggerFactory.getLogger(MyOkHttpTest.class);
    private static final OkHttpClient client = new OkHttpClient();
    private static final String url = "http://publicobject.com/helloworld.txt";
    @Test
    public void testExecute(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Request request = new Request.Builder().url(url).build();
        Response response;
        try {
            response = client.newCall(request).execute();
            if(response != null && response.isSuccessful()) {
                log.debug("code :" + String.valueOf(response.code()));
                log.debug("response: " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopWatch.split();
        log.info("cost time : " + stopWatch.getSplitTime()  + " ms");
    }

    @Test
    public void testEnqueue(){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.debug("onFailure :" + call.isExecuted());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    log.debug("code :" + String.valueOf(response.code()));
                    log.debug("response: " + response.body().string() );
                }
            });


        try {
            log.debug("latch.wait() :start");
            TimeUnit.SECONDS.sleep(5);
            log.debug("latch.wait() :end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
