package com.yatu.service;

import java.util.List;

import com.yatu.domain.ApplicationConfig;


/**
 * Created by hjf_mac on 10/9/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  11/01/2016 16:50
 */
public interface ApplicationService {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * findConfigInfo.
   *
   * @param   names  String
   *
   * @return  List
   */
  List<ApplicationConfig> findConfigInfo(String names);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findConfigInfoByConfigName.
   *
   * @param   name  String
   *
   * @return  ApplicationConfig
   */
  ApplicationConfig findConfigInfoByConfigName(String name);
}
