package com.yatu.test;



import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


import com.fasterxml.jackson.core.JsonParseException;
import com.yatu.util.SignNatureUtil;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by Yang Wang on 6/14/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/14/2016 12:13
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegerationTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Autowired public ObjectMapper objectMapper;

  /** TODO: DOCUMENT ME! */
  @Autowired public TestRestTemplate restTemplate;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * post.
   *
   * @param   <T>      Class
   * @param   url      String
   * @param   content  String
   * @param   t        Class
   *
   * @return  ResponseEntity
   */
  public <T> ResponseEntity<T> post(String url, String content, Class<T> t) throws IOException {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.set("appId", "1");
    requestHeaders.set("appName", "1");
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    Map<String, Object> map = objectMapper.readValue(content, TreeMap.class);
    String sign = SignNatureUtil.getSignNature(map, null);
    map.put("sign", sign);

    HttpEntity requestEntity = new HttpEntity(objectMapper.writeValueAsString(map), requestHeaders);

    ResponseEntity<T> responseEntity = this.restTemplate.postForEntity(url, requestEntity, t,
        new HashMap<String, Object>());

    return responseEntity;
  }
} // end class BaseIntegerationTest
