package com.yatu.test.integeration;


import java.io.IOException;

import java.util.Map;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.springframework.http.ResponseEntity;

import com.yatu.data.CommitWorksParams;
import com.yatu.data.FollowUserIdParams;
import com.yatu.data.PageParams;
import com.yatu.data.ResponseInfo;
import com.yatu.data.TopicIdParams;

import com.yatu.test.BaseIntegerationTest;
import com.yatu.test.util.FileUtil;


/**
 * Created by hjf_mac on 10/8/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/08/2016 15:45
 */
public class TopicITTest extends BaseIntegerationTest {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * testCreateTopic.
   *
   * @throws  IOException  exception
   */
  @Test public void testCreateTopic() throws IOException {
    String content = FileUtil.getFileContent("json/createTopicParams.json", "");

    ResponseEntity<ResponseInfo> responseEntity = post("/topic/createTopic",
        content, ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testGetTopicDetailById.
   *
   * @throws  IOException  exception
   */
  @Test public void testGetTopicDetailById() throws IOException {
    String        content       = FileUtil.getFileContent("json/baseParams.json", "");
    TopicIdParams topicIdParams = objectMapper.readValue(content, TopicIdParams.class);
    topicIdParams.setTid("1");

    ResponseEntity<ResponseInfo> responseEntity = post("/topic/getTopicDetailById",
        objectMapper.writeValueAsString(topicIdParams), ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testGetTopicList.
   *
   * @throws  IOException  exception
   */
  @Test public void testGetTopicList() throws IOException {
    String     content       = FileUtil.getFileContent("json/baseParams.json", "");

    ResponseEntity<ResponseInfo> responseEntity = post("/topic/getTopics",
        content, ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testIsFollowedUser.
   *
   * @throws  IOException  exception
   */
  @Test public void testSubmitDesign() throws IOException {
    String            content           = FileUtil.getFileContent("json/baseParams.json", "");
    CommitWorksParams commitWorksParams = objectMapper.readValue(content, CommitWorksParams.class);
    commitWorksParams.setTid("1");
    commitWorksParams.setEid("2");

    ResponseEntity<ResponseInfo> responseEntity = post("/topic/submitDesign",
        objectMapper.writeValueAsString(commitWorksParams), ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }
} // end class TopicITTest
