package com.yatu.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yatu.domain.User;
import com.yatu.domain.Works;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.util.StringUtils;

import com.yatu.domain.Topic;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/27/2016 11:44
 */
public class TopicDetailRowMapper implements RowMapper<Topic> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
   */
  @Override public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
    Topic topic = new Topic();
    topic.setId(rs.getString(1));
    topic.setContent(rs.getString("content"));
    topic.setTitle(rs.getString("title"));
    topic.setImages(rs.getString("images"));
    topic.setCreateDate(rs.getTimestamp(5));

    if (StringUtils.hasText(rs.getString(6))) {
      Works works = new Works();
      works.setId(rs.getString(6));
      works.setThumbnail(rs.getString("thumbnail"));
      works.setType(rs.getInt("type"));
      works.setContentUrl(rs.getString("contentUrl"));
      works.setCreateDate(rs.getTimestamp(12));
      works.setWidth(rs.getInt("width"));
      works.setHeight(rs.getInt("height"));

      if (StringUtils.hasText(rs.getString(13))) {
        User user = new User();
        user.setId(rs.getString(13));
        user.setNickname(rs.getString("nickname"));
        user.setAvatar(rs.getString("avatar"));
        works.setUser(user);
      }

      topic.setWorks(works);
    }

    return topic;
  } // end method mapRow
} // end class TopicDetailRowMapper
