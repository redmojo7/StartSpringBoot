package com;

import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/19.
 */
public class studyGuava {
  
  @Test
  public void main() throws UnsupportedEncodingException {
    String[] args = {"a"};
    studyPreConditions(args);
  }
  
  public void studyPreConditions(String[] args) throws UnsupportedEncodingException {
    Preconditions.checkNotNull(args);
    Preconditions.checkNotNull(args[0]);
  
    String decodedString = args[0];
  
    System.out.println("Decoded String: " + decodedString);
  
    String encodedString = BaseEncoding.base64Url().encode(decodedString.getBytes("UTF-8"));
  
    System.out.println("Encoded String: " + encodedString);
  }
  
  
}
