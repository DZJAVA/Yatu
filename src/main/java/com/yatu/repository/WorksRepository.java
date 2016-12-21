package com.yatu.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;

import com.yatu.data.PageParams;

import com.yatu.domain.Works;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:41
 */
@Repository public class WorksRepository {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private JdbcTemplate jdbcTemplate;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * createWorks.
   *
   * @param   works  Works
   *
   * @return  Works
   */
  public Works createWorks(Works works) {
    final String sql = "insert into works(id, width, height, type, thumbnail, contentUrl, userId, createDate)"
      + " values(?, ?, ?, ?, ?, ?, ?, ?)";

    jdbcTemplate.update(sql,
      new Object[] {
        works.getId(),
        works.getWidth(),
        works.getHeight(),
        works.getType(),
        works.getThumbnail(),
        works.getContentUrl(),
        works.getUser().getId(),
        new Timestamp(System.currentTimeMillis())
      });

    return works;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findPublicWorks.
   *
   * @param   page  PageParams
   *
   * @return  List
   */
  public List<Works> findPublicWorks(PageParams page) {
    final String sql = "select w.*, u.nickname, u.avatar from works w left join `user` u on w.userId = u.id order by w.createDate desc limit ?, ?";

    return jdbcTemplate.query(sql, new Object[] { page.getStart(), page.getCount() }, new WorksRowMapper());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserLikedWorks.
   *
   * @param   uid   String
   * @param   wids  String
   *
   * @return  List
   */
  public List<Map<String, Object>> findUserLikedWorks(String uid, List wids) {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    final String               sql                        =
      "select worksId from worksLiked where liked = 1 and userId = :userId and worksId in (:wids)";

    Map params = new HashMap<>();
    params.put("userId", uid);
    params.put("wids", wids);

    return namedParameterJdbcTemplate.queryForList(sql, params);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findWoksIsLiked.
   *
   * @param   wids  String
   *
   * @return  List
   */
  public List<String> findWoksIsLiked(List<String> wids) {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    Map                        params                     = new HashMap<>();
    params.put("wids", wids);

    final String sql = "select worksId from worksLiked wl where wl.liked = 1 and wl.worksId in(:wids)";

    return namedParameterJdbcTemplate.queryForList(sql, params, String.class);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findWorksById.
   *
   * @param   wid  String
   *
   * @return  List
   */
  public List<Works> findWorksById(String wid) {
    final String sql =
      "select w.id, w.width, w.height, w.thumbnail, w.contentUrl, w.createDate, w.type, w.userId, u.nickname, u.avatar, wl.liked, u1.id, u1.nickname, u1.avatar from works w left join `user` u on w.userId = u.id left join worksLiked wl on w.id = wl.worksId left join `user` u1 on wl.userId = u1.id where w.id = ?";

    return jdbcTemplate.query(sql, new Object[] { wid }, new WorksLikedRowMapper());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findWorksByUid.
   *
   * @param   uid    String
   * @param   start  int
   * @param   count  int
   *
   * @return  List
   */
  public List<Works> findWorksByUid(String uid, int start, int count) {
    final String sql = "select * from works w where w.userId = ? order by w.createDate desc limit ?, ?";

    return jdbcTemplate.query(sql, new Object[] { uid, start, count }, new UserWorksRowMapper());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for existed works.
   *
   * @param   wid  String
   *
   * @return  Integer
   */
  public Integer isExistedWorks(String wid) {
    final String sql = "select count(*) from works w where w.id = ?";

    return jdbcTemplate.queryForObject(sql, new Object[] { wid }, Integer.class);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for liked works.
   *
   * @param   wid  String
   * @param   uid  String
   *
   * @return  int
   */
  public List<String> isLikedWorks(String wid, String uid) {
    final String sql = "select liked from worksLiked where worksId = ? and userId = ?";

    return jdbcTemplate.query(sql, new Object[]{wid, uid}, new RowMapper<String>() {
      @Override
      public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(1);
      }
    });
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * likedWorkds.
   *
   * @param  isExisted  boolean
   * @param  liked      int
   * @param  wid        String
   * @param  uid        String
   */
  public void likedWorkds(boolean isExisted, int liked, String wid, String uid) {
    if (isExisted) {
      final String sql = "update worksLiked set liked = ?, lastUpdateDate = ? where worksId = ? and userId = ?";
      jdbcTemplate.update(sql, new Object[] { liked, new Timestamp(System.currentTimeMillis()), wid, uid });
    } else {
      final String sql = "insert into worksLiked(worksId, userId, liked, createDate) values(?, ?, ?, ?)";
      jdbcTemplate.update(sql, new Object[] { wid, uid, liked, new Timestamp(System.currentTimeMillis()) });
    }
  }
} // end class WorksRepository
