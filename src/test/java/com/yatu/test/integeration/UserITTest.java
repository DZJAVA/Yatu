package com.yatu.test.integeration;

import java.io.IOException;

import java.util.Map;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.springframework.http.ResponseEntity;

import org.springframework.util.StringUtils;

import com.yatu.data.FollowUserIdParams;
import com.yatu.data.FollowUserParams;
import com.yatu.data.ResponseInfo;
import com.yatu.data.UserIdParams;
import com.yatu.data.UserPageParams;

import com.yatu.test.BaseIntegerationTest;
import com.yatu.test.util.FileUtil;


/**
 * Created by hjf_mac on 10/8/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/08/2016 15:18
 */
public class UserITTest extends BaseIntegerationTest {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * testCreateDesign.
   *
   * @throws  IOException  exception
   */
  @Test public void testCreateUser() throws IOException {
    String content = FileUtil.getFileContent("json/createUserParams.json", "");

    ResponseEntity<ResponseInfo> responseEntity = post("/user/thirdPartyLogin", content, ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(StringUtils.hasText(responseInfo.getData().get("userId")));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFollowUser.
   *
   * @throws  IOException  exception
   */
  @Test public void testFollowUser() throws IOException {
    String           content          = FileUtil.getFileContent("json/baseParams.json", "");
    FollowUserParams followUserParams = objectMapper.readValue(content, FollowUserParams.class);
    followUserParams.setFollowed(true);
    followUserParams.setFollowUId("2");

    ResponseEntity<ResponseInfo> responseEntity = post("/user/followUser",
        objectMapper.writeValueAsString(followUserParams), ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testGetFollowedUsers.
   *
   * @throws  IOException  exception
   */
  @Test public void testGetFollowedUsers() throws IOException {
    String         content        = FileUtil.getFileContent("json/baseParams.json", "");
    UserPageParams userPageParams = objectMapper.readValue(content, UserPageParams.class);
    userPageParams.setUid("1");

    ResponseEntity<ResponseInfo> responseEntity = post("/user/getFollowedUsers",
        objectMapper.writeValueAsString(userPageParams), ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testGetUserDesignsList.
   *
   * @throws  IOException  exception
   */
  @Test public void testGetUserDesignsList() throws IOException {
    String         content        = FileUtil.getFileContent("json/baseParams.json", "");
    UserPageParams userPageParams = objectMapper.readValue(content, UserPageParams.class);
    userPageParams.setUid("1");

    ResponseEntity<ResponseInfo> responseEntity = post("/user/getUserDesigns",
        objectMapper.writeValueAsString(userPageParams), ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testGetUserDetailById.
   *
   * @throws  IOException  exception
   */
  @Test public void testGetUserDetailById() throws IOException {
    String       content      = FileUtil.getFileContent("json/baseParams.json", "");
    UserIdParams userIdParams = objectMapper.readValue(content, UserIdParams.class);
    userIdParams.setUid("1");

    ResponseEntity<ResponseInfo> responseEntity = post("/user/getUserDetailById",
        objectMapper.writeValueAsString(userIdParams), ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testGetUserFans.
   *
   * @throws  IOException  exception
   */
  @Test public void testGetUserFans() throws IOException {
    String         content        = FileUtil.getFileContent("json/baseParams.json", "");
    UserPageParams userPageParams = objectMapper.readValue(content, UserPageParams.class);
    userPageParams.setUid("1");

    ResponseEntity<ResponseInfo> responseEntity = post("/user/getUserFans",
        objectMapper.writeValueAsString(userPageParams), ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testIsFollowedUser.
   *
   * @throws  IOException  exception
   */
  @Test public void testIsFollowedUser() throws IOException {
    String             content          = FileUtil.getFileContent("json/baseParams.json", "");
    FollowUserIdParams followUserParams = objectMapper.readValue(content, FollowUserIdParams.class);
    followUserParams.setFollowUId("2");

    ResponseEntity<ResponseInfo> responseEntity = post("/user/isFollowedUser",
        objectMapper.writeValueAsString(followUserParams), ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }
} // end class UserITTest
