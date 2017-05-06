package com.strat.springboot.Controller.service;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * MyOkHttpTest
 *
 * @author : Donald Cai
 * @date :   2017/5/6.
 */
@Service
@Transactional
public class MyOkHttpService {

    private static final Logger log = LoggerFactory.getLogger(MyOkHttpService.class);
    private static final OkHttpClient client = new OkHttpClient();
    private static final String url = "http://publicobject.com/helloworld.txt";
    public void testExecute(){
        Request request = new Request.Builder().url(url).build();
        Response response;
        try {
            response = client.newCall(request).execute();
            if(response != null && response.isSuccessful()) {
                log.info("code :" + String.valueOf(response.code()));
                log.info("response: " + response.body().string() );
            }
            log.info("return result.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testEnqueue(){
        Request request = new Request.Builder().url(url).build();
        CountDownLatch latch = new CountDownLatch(1);
        client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.debug("onFailure :" + call.isExecuted());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    log.info("code :" + String.valueOf(response.code()));
                    log.info("response: " + response.body().string() );
                    latch.countDown();
                    log.info("latch.countDown(),count:" + latch.getCount());
                }
            });
        log.info("return result.");

//        try {
//            log.debug("latch.wait() :start");
//            latch.wait();
//            log.debug("latch.wait() :end");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //waiting
    }
}
