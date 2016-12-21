package com.yatu.service;

import java.util.List;

import com.yatu.data.CommitWorksParams;
import com.yatu.data.PageParams;
import com.yatu.data.TopicIdParams;
import com.yatu.domain.Topic;
import com.yatu.exception.InvalidParamsException;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/27/2016 14:20
 */
public interface TopicService {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * commitWorks.
   *
   * @param   commitWorksParams  CommitWorksParams
   *
   * @throws  InvalidParamsException  exception
   */
  void commitWorks(CommitWorksParams commitWorksParams) throws InvalidParamsException;

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * createTopic.
   *
   * @param   topic  Topic
   *
   * @return  Topic
   */
  Topic createTopic(Topic topic);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findTopicById.
   *
   * @param   topicIdParams  TopicIdParams
   *
   * @return  List
   *
   * @throws  InvalidParamsException  exception
   */
  List<Topic> findTopicById(TopicIdParams topicIdParams) throws InvalidParamsException;

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findTopicsByPage.
   *
   * @param   pageParams  PageParams
   *
   * @return  List
   */
  List<Topic> findTopicsByPage(PageParams pageParams);
} // end interface TopicService
