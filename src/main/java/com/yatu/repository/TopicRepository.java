package com.yatu.repository;

import java.sql.Timestamp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;

import com.yatu.domain.Topic;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:41
 */
@Repository public class TopicRepository {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private JdbcTemplate jdbcTemplate;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * commitWords.
   *
   * @param  tid  String
   * @param  wid  String
   * @param  uid  String
   */
  public void commitWorks(String tid, String wid, String uid) {
    final String sql = "insert into topicWorks(topicId, worksId, userId, createDate) values(?, ?, ?, ?)";

    jdbcTemplate.update(sql, new Object[] { tid, wid, uid, new Timestamp(System.currentTimeMillis()) });
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * createUser.
   *
   * @param   topic  user User
   *
   * @return  User
   */
  public Topic createTopic(Topic topic) {
    final String sql = "insert into topic(id, title, content, images, createUser, createDate) values(?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql,
      new Object[] {
        topic.getId(),
        topic.getTitle(),
        topic.getContent(),
        topic.getImages(),
        topic.getCreateUser().getId(),
        new Timestamp(System.currentTimeMillis())
      });

    return topic;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findTopicById.
   *
   * @param   tid    String
   * @param   start  int
   * @param   count  int
   *
   * @return  List
   */
  public List<Topic> findTopicById(String tid, int start, int count) {
    final String sql =
      "select t.id, t.content, t.title, t.images, t.createDate, w.id, w.width, w.height, w.thumbnail, w.type, w.contentUrl, w.createDate, u.id, u.nickname, u.avatar "
      + "from topic t left join topicWorks tw on t.id = tw.topicId left join works w on tw.worksId = w.id left join `user` u on w.userId = u.id where t.id = ? limit ?, ?";

    return jdbcTemplate.query(sql, new Object[] { tid, start, count }, new TopicDetailRowMapper());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findTopicByPage.
   *
   * @param   start  int
   * @param   count  int
   *
   * @return  List
   */
  public List<Topic> findTopicByPage(int start, int count) {
    final String sql = "select * from topic t limit ?, ?";

    return jdbcTemplate.query(sql, new Object[] { start, count }, new TopicRowMapper());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findTopicTotalById.
   *
   * @param   tid  String
   *
   * @return  List
   */
  public List<Map<String, Object>> findTopicTotalById(String tid) {
    final String sql =
      "select tw.worksId, wl.liked, count(*) from topicWorks tw left join worksLiked wl on tw.worksId = wl.worksId where (wl.liked = 1 or wl.liked is null) and tw.topicId = ? group by tw.worksId";

    return jdbcTemplate.queryForList(sql, new Object[] { tid });
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findTopicTotalByIds.
   *
   * @param   tids  String
   *
   * @return  List
   */
  public List<Map<String, Object>> findTopicTotalByIds(List<String> tids) {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    Map                        params                     = new HashMap<>();
    params.put("tids", tids);

    final String sql =
      "select tw.topicId, tw.worksId, wl.liked, count(*) from topicWorks tw left join worksLiked wl on tw.worksId = wl.worksId where (wl.liked = 1 or wl.liked is null) and tw.topicId in(:tids) group by tw.topicId, tw.worksId";

    return namedParameterJdbcTemplate.queryForList(sql, params);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for commit works.
   *
   * @param   tid  String
   * @param   wid  String
   *
   * @return  Integer
   */
  public Integer isCommitWorks(String tid, String wid) {
    final String sql = "select count(*) from topicWorks tw where tw.worksId = ? and tw.topicId = ?";

    return jdbcTemplate.queryForObject(sql, new Object[] { wid, tid }, Integer.class);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for existed topic.
   *
   * @param   tid  String
   *
   * @return  Integer
   */
  public Integer isExistedTopic(String tid) {
    final String sql = "select count(*) from topic t where t.id = ?";

    return jdbcTemplate.queryForObject(sql, new Object[] { tid }, Integer.class);
  }
} // end class TopicRepository
