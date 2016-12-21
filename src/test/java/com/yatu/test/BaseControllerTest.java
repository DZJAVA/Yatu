package com.yatu.test;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.yatu.data.BaseParams;
import org.junit.Rule;

import org.junit.rules.ExpectedException;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.yatu.domain.ApplicationConfig;

import com.yatu.service.ApplicationService;
import com.yatu.service.UserService;

import com.yatu.util.SignNatureUtil;


/**
 * Created by Yang Wang on 6/14/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/14/2016 12:13
 */
@ActiveProfiles("test")
@ComponentScan(value = { "com.yatu.aop" })
@EnableAspectJAutoProxy
@RunWith(SpringRunner.class)
public abstract class BaseControllerTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @MockBean public ApplicationService applicationService;

  /** TODO: DOCUMENT ME! */
  @Autowired public ObjectMapper objectMapper;

  /** TODO: DOCUMENT ME! */
  @Rule public ExpectedException thrown = ExpectedException.none();

  /** TODO: DOCUMENT ME! */
  @MockBean public UserService userService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for sign map.
   *
   * @param   content  String
   *
   * @return  Map
   *
   * @throws  IOException  exception
   */
  public Map getSignMap(String content) throws IOException {
    Map<String, Object> map = objectMapper.readValue(content, TreeMap.class);

    String sign = SignNatureUtil.getSignNature(map, null);

    map.put("sign", sign);

    return map;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for sign.
   *
   * @param   content  String
   *
   * @return  String
   *
   * @throws  IOException  exception
   */
  public void setSignField(String content, BaseParams baseParams) throws IOException {
    Map<String, Object> map = objectMapper.readValue(content, TreeMap.class);


    String sign = SignNatureUtil.getSignNature(map, null);

    baseParams.setSign(sign);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * initConfigList.
   *
   * @return  List
   */
  public List<ApplicationConfig> initConfigList() {
    List<ApplicationConfig> list = new ArrayList<>();
    list.add(new ApplicationConfig("appID", "1"));
    list.add(new ApplicationConfig("appName", "1"));

    return list;
  }

} // end class BaseControllerTest
