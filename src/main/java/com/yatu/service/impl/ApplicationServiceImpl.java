package com.yatu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.yatu.domain.ApplicationConfig;

import com.yatu.repository.ApplicationConfigRepository;

import com.yatu.service.ApplicationService;


/**
 * Created by hjf_mac on 10/9/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/09/2016 16:51
 */
@Service public class ApplicationServiceImpl implements ApplicationService {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Autowired ApplicationConfigRepository applicationConfigRepository;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.ApplicationService#findConfigInfo(java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public List<ApplicationConfig> findConfigInfo(String names) {
    return applicationConfigRepository.findConfigInfo(names);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.ApplicationService#findConfigInfoByConfigName(java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public ApplicationConfig findConfigInfoByConfigName(String name) {
    return applicationConfigRepository.findConfigInfoByConfigName(name);
  }
} // end class ApplicationServiceImpl
