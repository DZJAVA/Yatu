package com.yatu.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yatu.domain.User;
import com.yatu.domain.Works;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.util.StringUtils;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/26/2016 15:45
 */
public class SingleUserRowMapper implements RowMapper<User> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
   */
  @Override public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
    user.setId(rs.getString("id"));
    user.setNickname(rs.getString("nickname"));
    user.setAvatar(rs.getString("avatar"));
    user.setSex((rs.getObject("sex") == null) ? null : rs.getInt("sex"));

    if (StringUtils.hasText(rs.getString(5))) {
      Works works = new Works();
      works.setId(rs.getString(5));
      works.setThumbnail(rs.getString("thumbnail"));
      user.setWorks(works);
    }

    return user;
  }
}