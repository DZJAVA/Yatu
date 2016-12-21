package com.yatu.repository;

import com.yatu.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:40
 */
public class SimpleUserRowMapper implements RowMapper<User> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
   */
  @Override public User mapRow(ResultSet rs, int rowNum) throws SQLException {

    User user = new User();
    user.setId(rs.getString("id"));
    user.setNickname(rs.getString("nickname"));
    user.setAvatar(rs.getString("avatar"));

    return user;
  }
} // end class WorksRowMapper
