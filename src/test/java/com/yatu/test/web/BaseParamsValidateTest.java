package com.yatu.test.web;

import static org.junit.Assert.assertTrue;

import com.yatu.domain.ApplicationConfig;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yatu.controller.WorksController;

import com.yatu.data.PageParams;
import com.yatu.data.WorksIdParams;

import com.yatu.service.WorksService;

import com.yatu.test.BaseControllerTest;
import com.yatu.test.util.FileUtil;


/**
 * Created by hjf_mac on 10/9/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/09/2016 16:34
 */
@WebMvcTest(WorksController.class)
public class BaseParamsValidateTest extends BaseControllerTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private MockMvc mvc;

  @MockBean private WorksService worksService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * init.
   */
  @Before public void init() {
    given(this.applicationService.findConfigInfo("appID,appName")).willReturn(initConfigList());
    given(this.applicationService.findConfigInfoByConfigName("appKey")).willReturn(new ApplicationConfig("appKey",
        "q87sdfskdhfksdjf343"));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * test.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullAppID() throws Exception {
    String content = FileUtil.getFileContent("json/baseParams.json", null);
    this.mvc.perform(post("/design/getPublicDesigns").content(objectMapper.writeValueAsString(getSignMap(content))).contentType(MediaType.APPLICATION_JSON).accept(
        MediaType.APPLICATION_JSON)).andExpect(status().is(401));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullAppKey.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullAppKey() throws Exception {
    String     content    = FileUtil.getFileContent("json/baseParams.json", null);
    PageParams pageParams = objectMapper.readValue(content, PageParams.class);
    pageParams.setAppKey(null);

    setSignField(objectMapper.writeValueAsString(pageParams), pageParams);

  this.mvc.perform(post("/design/getPublicDesigns").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(pageParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(401));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullCount.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullCount() throws Exception {
    String     content    = FileUtil.getFileContent("json/baseParams.json", null);
    PageParams pageParams = objectMapper.readValue(content, PageParams.class);
    pageParams.setCount(null);

    setSignField(objectMapper.writeValueAsString(pageParams), pageParams);

    MvcResult mvcResult = this.mvc.perform(post("/design/getPublicDesigns").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(pageParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("count is required"));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullDevice.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullDevice() throws Exception {
    String     content    = FileUtil.getFileContent("json/baseParams.json", null);
    PageParams pageParams = objectMapper.readValue(content, PageParams.class);
    pageParams.setDevice(null);

    setSignField(objectMapper.writeValueAsString(pageParams), pageParams);

    MvcResult mvcResult = this.mvc.perform(post("/design/getPublicDesigns").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(pageParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("device is required"));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullPage.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullPage() throws Exception {
    String     content    = FileUtil.getFileContent("json/baseParams.json", null);
    PageParams pageParams = objectMapper.readValue(content, PageParams.class);
    pageParams.setPage(null);

    setSignField(objectMapper.writeValueAsString(pageParams), pageParams);

    MvcResult mvcResult = this.mvc.perform(post("/design/getPublicDesigns").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(pageParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("page is required"));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullSign.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullSign() throws Exception {
    String     content    = FileUtil.getFileContent("json/baseParams.json", null);
    PageParams pageParams = objectMapper.readValue(content, PageParams.class);
    pageParams.setSign(null);

    this.mvc.perform(post("/design/getPublicDesigns").header("appID", "1").header("appName", "1").content(
        objectMapper.writeValueAsString(pageParams)).contentType(MediaType.APPLICATION_JSON).accept(
        MediaType.APPLICATION_JSON)).andExpect(status().is(401));
// assertTrue(mvcResult.getResponse().getContentAsString().contains("sign is required"));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullUuid.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullUuid() throws Exception {
    String     content    = FileUtil.getFileContent("json/baseParams.json", null);
    PageParams pageParams = objectMapper.readValue(content, PageParams.class);
    pageParams.setUuid(null);

    setSignField(objectMapper.writeValueAsString(pageParams), pageParams);

    MvcResult mvcResult = this.mvc.perform(post("/design/getPublicDesigns").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(pageParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("uuid is required"));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullVersion.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullVersion() throws Exception {
    String     content    = FileUtil.getFileContent("json/baseParams.json", null);
    PageParams pageParams = objectMapper.readValue(content, PageParams.class);
    pageParams.setVersion(null);

    setSignField(objectMapper.writeValueAsString(pageParams), pageParams);

    MvcResult mvcResult = this.mvc.perform(post("/design/getPublicDesigns").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(pageParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("version is required"));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testWrongSign.
   *
   * @throws  Exception  exception
   */
  @Test public void testWrongSign() throws Exception {
    String     content    = FileUtil.getFileContent("json/baseParams.json", null);
    PageParams pageParams = objectMapper.readValue(content, PageParams.class);

    this.mvc.perform(post("/design/getPublicDesigns").header("appID", "1").header("appName", "1").content(
        objectMapper.writeValueAsString(pageParams)).contentType(MediaType.APPLICATION_JSON).accept(
        MediaType.APPLICATION_JSON)).andExpect(status().is(401));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testWrongUserToken.
   *
   * @throws  Exception  exception
   */
  @Test public void testWrongUserToken() throws Exception {
    String        content       = FileUtil.getFileContent("json/baseParams.json", null);
    WorksIdParams worksIdParams = objectMapper.readValue(content, WorksIdParams.class);
    worksIdParams.setEid("1");
    given(this.userService.validateUserToken(worksIdParams.getUserid(), worksIdParams.getToken())).willReturn(0);

    setSignField(objectMapper.writeValueAsString(worksIdParams), worksIdParams);

    this.mvc.perform(post("/design/isLikedDesign").header("appID", "1").header("appName", "1").content(
        objectMapper.writeValueAsString(worksIdParams)).contentType(MediaType.APPLICATION_JSON).accept(
        MediaType.APPLICATION_JSON)).andExpect(status().is(401));
  }


} // end class BaseParamsValidateTest
