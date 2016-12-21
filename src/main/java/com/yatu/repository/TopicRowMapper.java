package com.yatu.repository;

import com.yatu.domain.Topic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:40
 */
public class TopicRowMapper implements RowMapper<Topic> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
   */
  @Override public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
    Topic topic = new Topic();
    topic.setId(rs.getString("id"));
    topic.setContent(rs.getString("content"));
    topic.setTitle(rs.getString("title"));
    topic.setImages(rs.getString("images"));
    topic.setCreateDate(rs.getTimestamp("createDate"));

    return topic;
  }
} // end class WorksRowMapper
