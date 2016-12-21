package com.yatu.service;


import java.util.List;

import com.yatu.data.UserPageParams;
import com.yatu.domain.User;
import com.yatu.domain.Works;
import com.yatu.exception.InvalidParamsException;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/26/2016 17:12
 */
public interface UserService {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * createOrUpdateUser.
   *
   * @param   user  User
   *
   * @return  User
   */
  User createOrUpdateUser(User user);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserById.
   *
   * @param   uid  String
   *
   * @return  List
   */
  List<User> findUserById(String uid) throws InvalidParamsException;

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserFans.
   *
   * @param   userPageParams  UserPageParams
   *
   * @return  List
   */
  List<User> findUserFans(UserPageParams userPageParams) throws InvalidParamsException;

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserFollows.
   *
   * @param   userPageParams  UserPageParams
   *
   * @return  List
   */
  List<User> findUserFollows(UserPageParams userPageParams) throws InvalidParamsException;

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserWorksByPage.
   *
   * @param   userPageParams  UserPageParams
   *
   * @return  List
   */
  List<Works> findUserWorksByPage(UserPageParams userPageParams) throws InvalidParamsException;

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * followUser.
   *
   * @param  uid        String
   * @param  followUid  String
   * @param  followed   int
   */
  void followUser(String uid, String followUid, int followed) throws InvalidParamsException;

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for followed user.
   *
   * @param   uid        String
   * @param   followUid  String
   *
   * @return  boolean
   */
  boolean isFollowedUser(String uid, String followUid) throws InvalidParamsException;

  Integer validateUserToken(String uid, String token);
} // end interface UserService
