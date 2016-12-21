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

import com.yatu.data.LikeWorksParams;
import com.yatu.data.WorksIdParams;
import com.yatu.data.WorksParams;

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
public class WorksControllerTest extends BaseControllerTest {
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
    given(this.userService.validateUserToken("1", "1")).willReturn(1);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullStatusIdWhenLikeWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testErrorStatusIdWhenLikeWorks() throws Exception {
    String content = FileUtil.getFileContent("json/likedWorksParams.json", null);

    this.mvc.perform(post("/design/likeDesign").header("appID", "1").header("appName", "1").content(
        objectMapper.writeValueAsString(getSignMap(content))).contentType(MediaType.APPLICATION_JSON).accept(
        MediaType.APPLICATION_JSON)).andExpect(status().is(400));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullHeightWhenCreateWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullHeightWhenCreateWorks() throws Exception {
    String      content     = FileUtil.getFileContent("json/createWorksParams.json", null);
    WorksParams worksParams = objectMapper.readValue(content, WorksParams.class);
    worksParams.setHeight(null);

    setSignField(objectMapper.writeValueAsString(worksParams), worksParams);


    MvcResult mvcResult = this.mvc.perform(post("/design/createDesign").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(worksParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("height is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullStatusIdWhenLikeWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullStatusIdWhenLikeWorks() throws Exception {
    String          content         = FileUtil.getFileContent("json/baseParams.json", null);
    LikeWorksParams likeWorksParams = objectMapper.readValue(content, LikeWorksParams.class);
    likeWorksParams.setEid("1");
    likeWorksParams.setLiked(null);

    setSignField(objectMapper.writeValueAsString(likeWorksParams), likeWorksParams);


    MvcResult mvcResult = this.mvc.perform(post("/design/likeDesign").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(likeWorksParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("liked status is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullThumbnailWhenCreateWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullThumbnailWhenCreateWorks() throws Exception {
    String      content     = FileUtil.getFileContent("json/createWorksParams.json", null);
    WorksParams worksParams = objectMapper.readValue(content, WorksParams.class);
    worksParams.setThumbnail(null);

    setSignField(objectMapper.writeValueAsString(worksParams), worksParams);


    MvcResult mvcResult = this.mvc.perform(post("/design/createDesign").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(worksParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("thumbnail is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullTypeWhenCreateWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullTypeWhenCreateWorks() throws Exception {
    String      content     = FileUtil.getFileContent("json/createWorksParams.json", null);
    WorksParams worksParams = objectMapper.readValue(content, WorksParams.class);
    worksParams.setType(null);

    setSignField(objectMapper.writeValueAsString(worksParams), worksParams);


    MvcResult mvcResult = this.mvc.perform(post("/design/createDesign").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(worksParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("type is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullWidthWhenCreateWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullWidthWhenCreateWorks() throws Exception {
    String      content     = FileUtil.getFileContent("json/createWorksParams.json", null);
    WorksParams worksParams = objectMapper.readValue(content, WorksParams.class);
    worksParams.setWidth(null);

    setSignField(objectMapper.writeValueAsString(worksParams), worksParams);


    MvcResult mvcResult = this.mvc.perform(post("/design/createDesign").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(worksParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("width is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullAppKey.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullWorksIdWhenFindWorksByID() throws Exception {
    String        content       = FileUtil.getFileContent("json/baseParams.json", null);
    WorksIdParams worksIdParams = objectMapper.readValue(content, WorksIdParams.class);
    worksIdParams.setEid(null);

    setSignField(objectMapper.writeValueAsString(worksIdParams), worksIdParams);

    MvcResult mvcResult = this.mvc.perform(post("/design/getDesignDetailById").header("appID", "1").header("appName",
          "1").content(objectMapper.writeValueAsString(worksIdParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("eid is required."));
  }


} // end class WorksControllerTest
