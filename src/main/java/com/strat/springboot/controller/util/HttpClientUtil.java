package com.strat.springboot.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * @author : Donald
 * @date : 2017/11/3 17:23.
 * @description :
 */
public class HttpClientUtil {
    private static final CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";
    
    static {
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(60000)
            .setSocketTimeout(15000)
            .build();
        httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(config)
            .build();
    }
    
    public static String httpGet(String url, Map<String, String> params){
        return httpGet(url, params,"utf-8");
    }
    /**
     * @param url http://taobao.com/test.action
     * @param params 参数,编码之前的参数
     * @return
     */
    public static String httpGet(String url, Map<String, String> params,String charset) {
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpget);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpget.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
