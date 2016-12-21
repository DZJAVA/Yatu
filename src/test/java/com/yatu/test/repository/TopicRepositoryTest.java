package com.yatu.test.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertTrue;

import com.yatu.test.BaseRepositoryTest;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yatu.data.PageParams;
import com.yatu.data.TopicParams;
import com.yatu.data.WorksParams;

import com.yatu.domain.Topic;
import com.yatu.domain.Works;

import com.yatu.repository.TopicRepository;
import com.yatu.repository.WorksRepository;

import com.yatu.test.util.FileUtil;

import com.yatu.util.UUIDGenerator;


/**
 * Created by hjf_mac on 10/11/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/11/2016 14:52
 */
public class TopicRepositoryTest extends BaseRepositoryTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------


  /** TODO: DOCUMENT ME! */
  @Autowired TopicRepository topicRepository;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * testCreateTopic.
   *
   * @throws  Exception  exception
   */
  @Test public void testCreateTopic() throws Exception {
    String      content     = FileUtil.getFileContent("json/createTopicParams.json", null);
    TopicParams topicParams = this.objectMapper.readValue(content, TopicParams.class);
    Topic       topic       = topicParams.populate();
    topic.setId(UUIDGenerator.getUUID());

    Topic topic1 = this.topicRepository.createTopic(topic);

    assertThat(topic1).isNotNull();
    assertThat(topic1.getId()).isEqualTo(topic.getId());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindTopicById.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindTopicById() throws Exception {
    List<Topic> list = this.topicRepository.findTopicById("1", 0, 10);

    assertThat(list).isNotEmpty();
    assertThat(list.get(0).getId()).isEqualTo("1");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindTopicByPage.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindTopicByPage() throws Exception {
    List<Topic> list = this.topicRepository.findTopicByPage(0, 10);

    assertThat(list).isNotEmpty();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testIsCommitWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testIsCommitWorks() throws Exception {
    Integer result = this.topicRepository.isCommitWorks("1", "1");

    assertThat(result).isEqualTo(1);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testIsExistedTopic.
   *
   * @throws  Exception  exception
   */
  @Test public void testIsExistedTopic() throws Exception {
    Integer result = this.topicRepository.isExistedTopic("1");

    assertThat(result).isEqualTo(1);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testTopicTotalById.
   *
   * @throws  Exception  exception
   */
  @Test public void testTopicTotalById() throws Exception {
    List list = this.topicRepository.findTopicTotalById("1");

    assertThat(list).isNotEmpty();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testTopicTotalByIds.
   *
   * @throws  Exception  exception
   */
  @Test public void testTopicTotalByIds() throws Exception {
    List list = this.topicRepository.findTopicTotalById("1");

    assertThat(list).isNotEmpty();
  }

} // end class TopicRepositoryTest
