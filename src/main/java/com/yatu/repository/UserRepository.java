package com.yatu.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.List;

import com.yatu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:41
 */
@Repository public class UserRepository {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private JdbcTemplate jdbcTemplate;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * createUser.
   *
   * @param   user  User
   *
   * @return  User
   */
  public User createUser(User user) {
    final String sql =
      "insert into user(id, username, password, token, nickname, avatar, sex, type, createDate) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql,
      new Object[] {
        user.getId(),
        user.getUsername(),
        user.getPassword(),
        user.getToken(),
        user.getNickname(),
        user.getAvatar(),
        user.getSex(),
        user.getType(),
        new Timestamp(System.currentTimeMillis())
      });

    return user;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserById.
   *
   * @param   uid  String
   *
   * @return  List
   */
  public List<User> findUserById(String uid) {
    final String sql =
      "select u.id, u.nickname, u.sex, u.avatar, w.id, w.thumbnail from `user` u left join works w on u.id = w.userId where u.id = ? limit 5";

    return jdbcTemplate.query(sql, new Object[] { uid }, new SingleUserRowMapper());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserFans.
   *
   * @param   uid    String
   * @param   start  int
   * @param   count  int
   *
   * @return  List
   */
  public List<User> findUserFans(String uid, int start, int count) {
    final String sql =
      "select u.id, u.nickname, u.avatar from userFollow uf left join `user` u on uf.userId = u.id where uf.followUid = ? and uf.followed = 1 limit ?, ?";

    return jdbcTemplate.query(sql, new Object[] { uid, start, count },
        new SimpleUserRowMapper());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserFansCount.
   *
   * @param   uid  String
   *
   * @return  Integer
   */
  public Integer findUserFansCount(String uid) {
    final String sql = "select count(*) from userFollow uf where  uf.followUid = ? and uf.followed = 1";

    return jdbcTemplate.queryForObject(sql, new Object[] { uid }, Integer.class);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserFollow.
   *
   * @param   uid    String
   * @param   start  int
   * @param   count  int
   *
   * @return  List
   */
  public List<User> findUserFollow(String uid, int start, int count) {
    final String sql =
      "select u.id, u.nickname, u.avatar from userFollow uf left join `user` u on uf.followUid = u.id where uf.userId = ? and uf.followed = 1 limit ?, ?";

    return jdbcTemplate.query(sql, new Object[] { uid, start, count },
        new SimpleUserRowMapper());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserFollowCount.
   *
   * @param   uid  String
   *
   * @return  Integer
   */
  public Integer findUserFollowCount(String uid) {
    final String sql = "select count(*) from userFollow uf where  uf.userId = ? and uf.followed = 1";

    return jdbcTemplate.queryForObject(sql, new Object[] { uid }, Integer.class);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * followUser.
   *
   * @param  isExisted  boolean
   * @param  followed   int
   * @param  uid        String
   * @param  followUid  String
   */
  public void followUser(boolean isExisted, int followed, String uid, String followUid) {
    if (isExisted) {
      final String sql = "update userFollow set followed = ?, lastUpdateDate = ? where userId = ? and followUid = ?";
      jdbcTemplate.update(sql, new Object[] { followed, new Timestamp(System.currentTimeMillis()), uid, followUid });
    } else {
      final String sql = "insert into userFollow(userId, followUid, followed, createDate) values(?, ?, ?, ?)";
      jdbcTemplate.update(sql, new Object[] { uid, followUid, followed, new Timestamp(System.currentTimeMillis()) });
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for existed user by id.
   *
   * @param   uid  String
   *
   * @return  Integer
   */
  public Integer isExistedUserById(String uid) {
    final String sql = "select count(*) from `user` u where u.id = ?";

    return jdbcTemplate.queryForObject(sql, new Object[] { uid }, Integer.class);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for existed user.
   *
   * @param   username  String
   *
   * @return  Integer
   */
  public List<String> isExistedUserByUsername(String username) {
    final String sql = "select u.id from `user` u where u.username = ?";

    return jdbcTemplate.queryForList(sql, new Object[] { username }, String.class);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for followed.
   *
   * @param   uid        String
   * @param   followUid  String
   *
   * @return  Map
   */
  public List<String> isFollowed(String uid, String followUid) {
    final String sql = "select followed from userFollow where userId = ? and followUid = ?";

    return jdbcTemplate.query(sql, new Object[]{uid, followUid}, new RowMapper<String>() {
      @Override
      public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(1);
      }
    });
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * updateUser.
   *
   * @param   user  User
   *
   * @return  User
   */
  public User updateUser(User user) {
    final String sql =
      "update `user` set `password` = ?, sex = ?, nickname = ?, token = ?, type = ?, avatar = ? where username = ?";
    jdbcTemplate.update(sql,
      new Object[] {
        user.getPassword(),
        user.getSex(),
        user.getNickname(),
        user.getToken(),
        user.getType(),
        user.getAvatar(),
        user.getUsername()
      });

    return user;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * validateUserToken.
   *
   * @param   uid    String
   * @param   token  String
   *
   * @return  Integer
   */
  public Integer validateUserToken(String uid, String token) {
    final String sql = "select count(*) from `user` u where u.id = ? and u.`password` = ?";

    return jdbcTemplate.queryForObject(sql, new Object[] { uid, token }, Integer.class);
  }
} // end class UserRepository
