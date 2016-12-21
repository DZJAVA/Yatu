package com.yatu.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yatu.domain.Works;
import org.springframework.jdbc.core.RowMapper;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/26/2016 16:29
 */
public class UserWorksRowMapper implements RowMapper<Works> {
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

    return works;
  }
}