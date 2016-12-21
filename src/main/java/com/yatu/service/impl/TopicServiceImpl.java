package com.yatu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.yatu.data.CommitWorksParams;
import com.yatu.data.ErrorCode;
import com.yatu.data.PageParams;
import com.yatu.data.TopicIdParams;

import com.yatu.domain.Topic;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.TopicRepository;
import com.yatu.repository.WorksRepository;

import com.yatu.service.TopicService;

import com.yatu.util.UUIDGenerator;


/**
 * Created by hjf_mac on 9/27/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/27/2016 13:53
 */
@Service public class TopicServiceImpl implements TopicService {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  private final TopicRepository topicRepository;

  /** TODO: DOCUMENT ME! */
  private final WorksRepository worksRepository;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new TopicServiceImpl object.
   *
   * @param  topicRepository  TopicRepository
   * @param  worksRepository  WorksRepository
   */
  public TopicServiceImpl(TopicRepository topicRepository, WorksRepository worksRepository) {
    this.topicRepository = topicRepository;
    this.worksRepository = worksRepository;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.TopicService#commitWorks(com.yatu.data.CommitWorksParams)
   */
  @Override @Transactional public void commitWorks(CommitWorksParams commitWorksParams) throws InvalidParamsException {
    Integer worksExisted = worksRepository.isExistedWorks(commitWorksParams.getEid());

    if (worksExisted.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.WORKS_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    Integer topicExisted = topicRepository.isExistedTopic(commitWorksParams.getTid());

    if (topicExisted.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.TOPIC_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    Integer isExisted = topicRepository.isCommitWorks(commitWorksParams.getTid(), commitWorksParams.getEid());

    if (isExisted.intValue() > 0) {
      throw new InvalidParamsException(ErrorCode.ALREADY_EXISTED.DESC(), ErrorCode.CONFLICT,
        HttpStatus.CONFLICT);
    }

    topicRepository.commitWorks(commitWorksParams.getTid(), commitWorksParams.getEid(), commitWorksParams.getUserid());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.TopicService#createTopic(com.yatu.domain.Topic)
   */
  @Override @Transactional public Topic createTopic(Topic topic) {
    topic.setId(UUIDGenerator.getUUID());

    return topicRepository.createTopic(topic);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.TopicService#findTopicById(com.yatu.data.TopicIdParams)
   */
  @Override
  @Transactional(readOnly = true)
  public List<Topic> findTopicById(TopicIdParams topicIdParams) throws InvalidParamsException {
    List<Topic> list = topicRepository.findTopicById(topicIdParams.getTid(), topicIdParams.getStart(),
        topicIdParams.getCount());

    if ((list != null) && (list.size() > 0)) {
      List<Map<String, Object>> result         = topicRepository.findTopicTotalById(topicIdParams.getTid());
      int                       expsCount      = 0;
      int                       attentionCount = 0;

      for (Map map : result) {
        expsCount++;

        if (map.get("liked") != null) {
          attentionCount += Integer.parseInt(map.get("count(*)").toString());
        }
      }

      List<String>  wids = new ArrayList<>();

      for (Topic t : list) {
        t.setExpsCount(expsCount);
        t.setAttentionCount(attentionCount);
        if (t.getWorks() != null) {
          wids.add(t.getWorks().getId());
        }
      }

      if (wids.size() > 0) {

        List<Map<String, Object>> worksList = worksRepository.findUserLikedWorks(topicIdParams.getUserid(), wids);

        for (Map<String, Object> map : worksList) {
          String worksId = map.get("worksId").toString();

          for (Topic t : list) {
            if (t.getWorks().getId().equals(worksId)) {
              t.getWorks().setLiked(1);
            }
          }
        }
      }
    } else {
      throw new InvalidParamsException(ErrorCode.TOPIC_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    } // end if-else

    return list;
  } // end method findTopicById

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.TopicService#findTopicsByPage(com.yatu.data.PageParams)
   */
  @Override
  @Transactional(readOnly = true)
  public List<Topic> findTopicsByPage(PageParams pageParams) {
    List<Topic> list = topicRepository.findTopicByPage(pageParams.getStart(), pageParams.getCount());

    if ((list != null) && (list.size() > 0)) {
      List<String> tids = new ArrayList<>();

      for (Topic topic : list) {
        tids.add(topic.getId());
      }

      List<Map<String, Object>> result = topicRepository.findTopicTotalByIds(tids);

      if ((result != null) && (result.size() > 0)) {
        for (Topic topic : list) {
          int expsCount      = 0;
          int attentionCount = 0;

          for (Map map : result) {
            if (topic.getId().equals(map.get("topicId").toString())) {
              expsCount++;

              if (map.get("liked") != null) {
                attentionCount += Integer.parseInt(map.get("count(*)").toString());
              }
            }
          }

          topic.setExpsCount(expsCount);
          topic.setAttentionCount(attentionCount);
        }
      }
    } // end if

    return list;
  } // end method findTopicsByPage
} // end class TopicServiceImpl
