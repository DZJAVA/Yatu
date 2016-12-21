package com.yatu.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;


import com.yatu.domain.ApplicationConfig;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:41
 */
@Repository public class ApplicationConfigRepository {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private JdbcTemplate jdbcTemplate;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * findTopicByPage.
   *
   * @param   configNames  String
   *
   * @return  List
   */
  @Cacheable(
    value = "ApplicationConfigs",
    key   = "'ApplicationConfigs'"
  )
  public List<ApplicationConfig> findConfigInfo(String configNames) {
    System.out.println("query app configs.....");

    final String sql = "select * from applicationConfig ac where ac.configName in ('appID', 'appName')";

    return jdbcTemplate.query(sql, new ApplicationConfigRowMapper());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findConfigInfoByConfigName.
   *
   * @param   configName  String
   *
   * @return  ApplicationConfig
   */
  @Cacheable(
    value = "ApplicationConfigs",
    key   = "'ApplicationConfig'.concat(#configName)"
  )
  public ApplicationConfig findConfigInfoByConfigName(String configName) {
    System.out.println("query app config.....");

    final String sql = "select * from applicationConfig ac where ac.configName = ?";

    List<ApplicationConfig> list = jdbcTemplate.query(sql, new Object[]{configName}, new ApplicationConfigRowMapper());

    return list.size() > 0 ? list.get(0) : null;
  }

} // end class ApplicationConfigRepository
