package com.yatu.repository;

import com.yatu.domain.ApplicationConfig;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:40
 */
public class ApplicationConfigRowMapper implements RowMapper<ApplicationConfig> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
   */
  @Override public ApplicationConfig mapRow(ResultSet rs, int rowNum) throws SQLException {

    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setConfigName(rs.getString("configName"));
    applicationConfig.setConfigValue(rs.getString("configValue"));

    return applicationConfig;
  }
} // end class WorksRowMapper
