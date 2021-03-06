package com.strat.springboot.controller.converter;

import com.strat.springboot.controller.domain.DemoObj;
import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

/**
 * @author : Donald
 * @date : 2017/12/5 15:55.
 * @description :
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {
    
    public MyMessageConverter() {
        super(new MediaType("application", "x-donald", Charset.forName("UTF-8")));//②
    }
    
    /**
     * ③
     */
    
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz,
        HttpInputMessage inputMessage) throws IOException,
        HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(),
            
            Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]), tempArr[1]);
    }
    
    /**
     * ④
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return DemoObj.class.isAssignableFrom(clazz);
    }
    
    /**
     * ⑤
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
        String out = "hello:" + obj.getId() + "-"+ obj.getName();
        outputMessage.getBody().write(out.getBytes());
    }
}
