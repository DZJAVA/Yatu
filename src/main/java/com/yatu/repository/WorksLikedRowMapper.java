package com.yatu.repository;

import com.yatu.domain.User;
import com.yatu.domain.Works;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:40
 */
public class WorksLikedRowMapper implements RowMapper<Works> {
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
    works.setCreateDate(rs.getDate("createDate"));
    works.setType(rs.getInt("type"));

    User user = new User();
    user.setId(rs.getString("userId"));
    user.setNickname(rs.getString(9));
    user.setAvatar(rs.getString(10));
    works.setUser(user);

    if (StringUtils.hasText(rs.getString(12))) {
      User user1 = new User();
      user1.setId(rs.getString(12));
      user1.setNickname(rs.getString(13));
      user1.setAvatar(rs.getString(14));
      works.setLikedUser(user1);
      works.setLiked(rs.getInt("liked"));
    }

    return works;
  }
} // end class WorksRowMapper
