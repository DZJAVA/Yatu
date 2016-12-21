package com.yatu.test.web;

import java.util.TreeMap;

import javax.servlet.annotation.WebFilter;

import static org.junit.Assert.assertTrue;

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

import com.yatu.controller.TopicController;

import com.yatu.data.CommitWorksParams;
import com.yatu.data.TopicIdParams;
import com.yatu.data.TopicParams;

import com.yatu.domain.ApplicationConfig;

import com.yatu.service.TopicService;

import com.yatu.test.BaseControllerTest;
import com.yatu.test.util.FileUtil;


/**
 * Created by hjf_mac on 10/9/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/09/2016 16:34
 */
@WebMvcTest(TopicController.class)
public class TopicControllerTest extends BaseControllerTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private MockMvc mvc;


  @MockBean private TopicService topicService;

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
   * testErrorImagesWhenCreateTopic.
   *
   * @throws  Exception  exception
   */
  @Test public void testErrorImagesWhenCreateTopic() throws Exception {
    String content = FileUtil.getFileContent("json/createTopicErrorImagesParams.json", null);

    this.mvc.perform(post("/topic/createTopic").header("appID", "1").header("appName", "1").content(
        objectMapper.writeValueAsString(getSignMap(content))).contentType(MediaType.APPLICATION_JSON).accept(
        MediaType.APPLICATION_JSON)).andExpect(status().is(400));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullHeightWhenCreateWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullContentWhenCreateTopic() throws Exception {
    String      content     = FileUtil.getFileContent("json/createTopicParams.json", null);
    TopicParams topicParams = objectMapper.readValue(content, TopicParams.class);
    topicParams.setContent(null);

    setSignField(objectMapper.writeValueAsString(topicParams), topicParams);


    MvcResult mvcResult = this.mvc.perform(post("/topic/createTopic").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(topicParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("content is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullTitleWhenCreateTopic.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullTitleWhenCreateTopic() throws Exception {
    String      content     = FileUtil.getFileContent("json/createTopicParams.json", null);
    TopicParams topicParams = objectMapper.readValue(content, TopicParams.class);
    topicParams.setTitle(null);

    setSignField(objectMapper.writeValueAsString(topicParams), topicParams);


    MvcResult mvcResult = this.mvc.perform(post("/topic/createTopic").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(topicParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("title is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNulltopicIdWhenGetTopicByID.
   *
   * @throws  Exception  exception
   */
  @Test public void testNulltopicIdWhenGetTopicByID() throws Exception {
    String        content       = FileUtil.getFileContent("json/baseParams.json", null);
    TopicIdParams topicIdParams = objectMapper.readValue(content, TopicIdParams.class);
    topicIdParams.setTid(null);

    setSignField(objectMapper.writeValueAsString(topicIdParams), topicIdParams);

    MvcResult mvcResult = this.mvc.perform(post("/topic/getTopicDetailById").header("appID", "1").header("appName",
          "1").content(objectMapper.writeValueAsString(topicIdParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("tid is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNulltopicIdWhenSubmitWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNulltopicIdWhenSubmitWorks() throws Exception {
    String            content           = FileUtil.getFileContent("json/baseParams.json", null);
    CommitWorksParams commitWorksParams = objectMapper.readValue(content, CommitWorksParams.class);
    commitWorksParams.setEid("1");
    commitWorksParams.setTid(null);

    setSignField(objectMapper.writeValueAsString(commitWorksParams), commitWorksParams);

    MvcResult mvcResult = this.mvc.perform(post("/topic/submitDesign").header("appID", "1").header("appName",
          "1").content(objectMapper.writeValueAsString(commitWorksParams)).contentType(MediaType.APPLICATION_JSON)
        .accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("tid is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullContentUrlWhenCreateWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullWorksIdWhenSubmitWorks() throws Exception {
    String            content           = FileUtil.getFileContent("json/baseParams.json", null);
    CommitWorksParams commitWorksParams = objectMapper.readValue(content, CommitWorksParams.class);
    commitWorksParams.setTid("1");
    commitWorksParams.setEid(null);

    setSignField(objectMapper.writeValueAsString(commitWorksParams), commitWorksParams);

    MvcResult mvcResult = this.mvc.perform(post("/topic/submitDesign").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(commitWorksParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("eid is required."));
  }

} // end class TopicControllerTest
