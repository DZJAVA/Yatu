package com.yatu.test.service;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import static org.mockito.BDDMockito.given;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yatu.data.CommitWorksParams;
import com.yatu.data.TopicIdParams;
import com.yatu.data.UserPageParams;

import com.yatu.domain.Topic;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.TopicRepository;
import com.yatu.repository.UserRepository;
import com.yatu.repository.WorksRepository;

import com.yatu.service.TopicService;
import com.yatu.service.UserService;
import com.yatu.service.impl.TopicServiceImpl;
import com.yatu.service.impl.UserServiceImpl;


/**
 * Created by hjf_mac on 10/11/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/11/2016 11:08
 */
public class TopicServiceTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Rule public ExpectedException thrown = ExpectedException.none();

  @Mock private TopicRepository topicRepository;

  private TopicService topicService;

  @Mock private WorksRepository worksRepository;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * setup.
   */
  @Before public void setup() {
    MockitoAnnotations.initMocks(this);
    this.topicService = new TopicServiceImpl(this.topicRepository, this.worksRepository);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testEmptyListFindTopicById.
   *
   * @throws  Exception  exception
   */
  @Test public void testEmptyListFindTopicById() throws Exception {
    given(this.topicRepository.findTopicById(anyString(), anyInt(), anyInt())).willReturn(new ArrayList<Topic>());
    thrown.expect(InvalidParamsException.class);
    thrown.expectMessage("Topic not found with the id.");

    TopicIdParams topicIdParams = new TopicIdParams();
    topicIdParams.setStart(1);
    topicIdParams.setCount(10);
    this.topicService.findTopicById(topicIdParams);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindUserFans.
   *
   * @throws  Exception  exception
   */
  @Test public void testInvalidTidCommitWorks() throws Exception {
    given(this.worksRepository.isExistedWorks(anyString())).willReturn(1);
    given(this.topicRepository.isExistedTopic(anyString())).willReturn(0);
    thrown.expect(InvalidParamsException.class);
    thrown.expectMessage("Topic not found with the id.");
    this.topicService.commitWorks(new CommitWorksParams());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindUserById.
   *
   * @throws  Exception  exception
   */
  @Test public void testInvalidWidCommitWorks() throws Exception {
    given(this.worksRepository.isExistedWorks(anyString())).willReturn(0);
    thrown.expect(InvalidParamsException.class);
    thrown.expectMessage("Design not found with the id.");
    this.topicService.commitWorks(new CommitWorksParams());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullFindTopicById.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullFindTopicById() throws Exception {
    given(this.topicRepository.findTopicById(anyString(), anyInt(), anyInt())).willReturn(null);
    thrown.expect(InvalidParamsException.class);
    thrown.expectMessage("Topic not found with the id.");

    TopicIdParams topicIdParams = new TopicIdParams();
    topicIdParams.setStart(1);
    topicIdParams.setCount(10);
    this.topicService.findTopicById(topicIdParams);
  }

} // end class TopicServiceTest
