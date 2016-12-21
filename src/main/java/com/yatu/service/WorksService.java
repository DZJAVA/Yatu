package com.yatu.service;

import com.yatu.data.PageParams;
import com.yatu.domain.Works;
import com.yatu.exception.InvalidParamsException;

import java.util.List;

/**
 * Created by hjf_mac on 9/22/16.
 */
public interface WorksService {
  public List<Works> findPublicWorks(PageParams page);

  public List<Works> findWorksById(String wid) throws InvalidParamsException;

  public void likeWorks(String uid, String wid, int liked) throws InvalidParamsException;

  public boolean isLikedWorks(String uid, String wid) throws InvalidParamsException;

  public Works createWorks(Works works);
}
