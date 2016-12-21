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

import com.yatu.controller.TopicController;
import com.yatu.controller.UserController;

import com.yatu.data.CommitWorksParams;
import com.yatu.data.FollowUserParams;
import com.yatu.data.TopicIdParams;
import com.yatu.data.TopicParams;
import com.yatu.data.UserIdParams;
import com.yatu.data.UserParams;

import com.yatu.service.TopicService;

import com.yatu.test.BaseControllerTest;
import com.yatu.test.util.FileUtil;


/**
 * Created by hjf_mac on 10/9/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/09/2016 16:34
 */
@WebMvcTest(UserController.class)
public class UserControllerTest extends BaseControllerTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private MockMvc mvc;

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
   * testErrorTypeWhenCreateUser.
   *
   * @throws  Exception  exception
   */
  @Test public void testErrorTypeWhenCreateUser() throws Exception {
    String content = FileUtil.getFileContent("json/createUserErrorIntegerParams.json", null);

    this.mvc.perform(post("/user/thirdPartyLogin").header("appID", "1").header("appName", "1").content(
        objectMapper.writeValueAsString(getSignMap(content))).contentType(MediaType.APPLICATION_JSON).accept(
        MediaType.APPLICATION_JSON)).andExpect(status().is(400));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullAvatarWhenCreateUser.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullAvatarWhenCreateUser() throws Exception {
    String     content    = FileUtil.getFileContent("json/createUserParams.json", null);
    UserParams userParams = objectMapper.readValue(content, UserParams.class);
    userParams.setAvatar(null);

    setSignField(objectMapper.writeValueAsString(userParams), userParams);


    MvcResult mvcResult = this.mvc.perform(post("/user/thirdPartyLogin").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(userParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("avatar is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullFollowUidWhenFollowUser.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullFollowUidWhenFollowUser() throws Exception {
    String           content          = FileUtil.getFileContent("json/baseParams.json", null);
    FollowUserParams followUserParams = objectMapper.readValue(content, FollowUserParams.class);
    followUserParams.setFollowed(true);
    followUserParams.setFollowUId(null);

    setSignField(objectMapper.writeValueAsString(followUserParams), followUserParams);


    MvcResult mvcResult = this.mvc.perform(post("/user/followUser").header("appID", "1").header("appName", "1").content(
          objectMapper.writeValueAsString(followUserParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("followUId is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullNameWhenCreateUser.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullNameWhenCreateUser() throws Exception {
    String     content    = FileUtil.getFileContent("json/createUserParams.json", null);
    UserParams userParams = objectMapper.readValue(content, UserParams.class);
    userParams.setName(null);

    setSignField(objectMapper.writeValueAsString(userParams), userParams);


    MvcResult mvcResult = this.mvc.perform(post("/user/thirdPartyLogin").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(userParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("name is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullOpenIdWhenCreateUser.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullOpenIdWhenCreateUser() throws Exception {
    String     content    = FileUtil.getFileContent("json/createUserParams.json", null);
    UserParams userParams = objectMapper.readValue(content, UserParams.class);
    userParams.setOpenId(null);

    setSignField(objectMapper.writeValueAsString(userParams), userParams);


    MvcResult mvcResult = this.mvc.perform(post("/user/thirdPartyLogin").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(userParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("openId is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullTokenWhenCreateUser.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullOpenTokenWhenCreateUser() throws Exception {
    String     content    = FileUtil.getFileContent("json/createUserParams.json", null);
    UserParams userParams = objectMapper.readValue(content, UserParams.class);
    userParams.setOpenToken(null);

    setSignField(objectMapper.writeValueAsString(userParams), userParams);

    MvcResult mvcResult = this.mvc.perform(post("/user/thirdPartyLogin").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(userParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("token is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullTypeWhenCreateUser.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullTypeWhenCreateUser() throws Exception {
    String     content    = FileUtil.getFileContent("json/createUserParams.json", null);
    UserParams userParams = objectMapper.readValue(content, UserParams.class);
    userParams.setType(null);

    setSignField(objectMapper.writeValueAsString(userParams), userParams);


    MvcResult mvcResult = this.mvc.perform(post("/user/thirdPartyLogin").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(userParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("type is required."));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullHeightWhenCreateWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullUidWhenGetUserByID() throws Exception {
    String       content      = FileUtil.getFileContent("json/baseParams.json", null);
    UserIdParams userIdParams = objectMapper.readValue(content, UserIdParams.class);
    userIdParams.setUid(null);

    setSignField(objectMapper.writeValueAsString(userIdParams), userIdParams);


    MvcResult mvcResult = this.mvc.perform(post("/user/getUserDetailById").header("appID", "1").header("appName", "1")
        .content(objectMapper.writeValueAsString(userIdParams)).contentType(MediaType.APPLICATION_JSON).accept(
          MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();
    assertTrue(mvcResult.getResponse().getContentAsString().contains("uid is required."));
  }

} // end class UserControllerTest
