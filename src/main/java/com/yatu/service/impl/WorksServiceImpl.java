package com.yatu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.yatu.data.ErrorCode;
import com.yatu.data.PageParams;

import com.yatu.domain.Works;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.WorksRepository;

import com.yatu.service.WorksService;

import com.yatu.util.UUIDGenerator;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/26/2016 11:19
 */
@Service public class WorksServiceImpl implements WorksService {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  private final WorksRepository worksRepository;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new WorksServiceImpl object.
   *
   * @param  worksRepository  WorksRepository
   */
  public WorksServiceImpl(WorksRepository worksRepository) {
    this.worksRepository = worksRepository;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.WorksService#createWorks(com.yatu.domain.Works)
   */
  @Override @Transactional public Works createWorks(Works works) {
    works.setId(UUIDGenerator.getUUID());

    return worksRepository.createWorks(works);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.WorksService#findPublicWorks(com.yatu.data.PageParams)
   */
  @Override
  @Transactional(readOnly = true)
  public List<Works> findPublicWorks(PageParams page) {
    List<Works> list = worksRepository.findPublicWorks(page);

    if (StringUtils.hasText(page.getUserid()) && (list != null) && (list.size() > 0)) {
      List<String> wids = new ArrayList<>();

      for (Works w : list) {
        wids.add(w.getId());
      }

      List<Map<String, Object>> result = worksRepository.findUserLikedWorks(page.getUserid(), wids);

      for (Map<String, Object> map : result) {
        String worksId = map.get("worksId").toString();

        for (Works works : list) {
          if (works.getId().equals(worksId)) {
            works.setLiked(1);
          }
        }
      }
    }

    return list;
  } // end method findPublicWorks

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.WorksService#findWorksById(java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public List<Works> findWorksById(String wid) throws InvalidParamsException {
    List<Works> list = worksRepository.findWorksById(wid);

    if ((list == null) || (list.size() == 0)) {
      throw new InvalidParamsException(ErrorCode.WORKS_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    return list;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.WorksService#isLikedWorks(java.lang.String, java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public boolean isLikedWorks(String uid, String wid) throws InvalidParamsException {
    Integer isExisted = worksRepository.isExistedWorks(wid);

    if (isExisted.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.WORKS_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    List<String> result = worksRepository.isLikedWorks(wid, uid);

    if (result.isEmpty()) {
      return false;
    }
    int liked = Integer.parseInt(result.get(0));

    if (liked == 0) {
      return false;
    }

    return true;
  } // end method isLikedWorks

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.WorksService#likeWorks(java.lang.String, java.lang.String, int)
   */
  @Override @Transactional public void likeWorks(String uid, String wid, int liked) throws InvalidParamsException {
    Integer isExistedWorks = worksRepository.isExistedWorks(wid);

    if (isExistedWorks.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.WORKS_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    List<String> result = worksRepository.isLikedWorks(wid, uid);
    boolean isExisted = true;

    if (result.isEmpty()) {
      isExisted = false;
    }

    worksRepository.likedWorkds(isExisted, liked, wid, uid);
  }
} // end class WorksServiceImpl
