package com.yatu.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yatu.domain.User;
import com.yatu.domain.Works;
import org.springframework.jdbc.core.RowMapper;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:40
 */
public class WorksRowMapper implements RowMapper<Works> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
   */
  @Override public Works mapRow(ResultSet rs, int rowNum) throws SQLException {
    Works works = new Works();
    works.setId(rs.getString("id"));
    works.setWidth(rs.getInt("width"));
    works.setHeight(rs.getInt("height"));
    works.setThumbnail(rs.getString("thumbnail"));
    works.setContentUrl(rs.getString("contentUrl"));
    works.setCreateDate(rs.getTimestamp("createDate"));
    works.setType(rs.getInt("type"));
//    works.setLiked((rs.getObject("liked") == null) ? 0 : rs.getInt("liked"));

    User user = new User();
    user.setId(rs.getString("userId"));
    user.setNickname(rs.getString("nickname"));
    user.setAvatar(rs.getString("avatar"));
    works.setUser(user);

    return works;
  }
} // end class WorksRowMapper
