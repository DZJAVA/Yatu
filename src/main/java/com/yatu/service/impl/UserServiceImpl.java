package com.yatu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.yatu.data.ErrorCode;
import com.yatu.data.UserPageParams;

import com.yatu.domain.User;
import com.yatu.domain.Works;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.UserRepository;
import com.yatu.repository.WorksRepository;

import com.yatu.service.UserService;

import com.yatu.util.UUIDGenerator;


/**
 * Created by hjf_mac on 9/26/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/26/2016 15:00
 */
@Service public class UserServiceImpl implements UserService {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  private final UserRepository userRepository;

  /** TODO: DOCUMENT ME! */
  private final WorksRepository worksRepository;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new UserServiceImpl object.
   *
   * @param  userRepository   UserRepository
   * @param  worksRepository  WorksRepository
   */
  public UserServiceImpl(UserRepository userRepository, WorksRepository worksRepository) {
    this.userRepository  = userRepository;
    this.worksRepository = worksRepository;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.UserService#createOrUpdateUser(com.yatu.domain.User)
   */
  @Override @Transactional public User createOrUpdateUser(User user) {
    List<String> uid = userRepository.isExistedUserByUsername(user.getUsername());

    if (uid.isEmpty()) {
      user.setId(UUIDGenerator.getUUID());
      userRepository.createUser(user);
    } else {
      user.setId(uid.get(0));
      userRepository.updateUser(user);
    }

    return user;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserById.
   *
   * @param   uid  String
   *
   * @return  List
   *
   * @throws  com.yatu.exception.InvalidParamsException  exception
   */
  @Override
  @Transactional(readOnly = true)
  public List<User> findUserById(String uid) throws InvalidParamsException {
    Integer isExisted = userRepository.isExistedUserById(uid);

    if (isExisted.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.USER_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    List<User> list        = userRepository.findUserById(uid);
    Integer    fansCount   = userRepository.findUserFansCount(uid);
    Integer    followCount = userRepository.findUserFollowCount(uid);

    for (User user : list) {
      user.setFansCount(fansCount);
      user.setFollowCount(followCount);
    }

    return list;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.UserService#findUserFans(com.yatu.data.UserPageParams)
   */
  @Override
  @Transactional(readOnly = true)
  public List<User> findUserFans(UserPageParams userPageParams) throws InvalidParamsException {
    Integer isExisted = userRepository.isExistedUserById(userPageParams.getUid());

    if (isExisted.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.USER_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    return userRepository.findUserFans(userPageParams.getUid(), userPageParams.getStart(), userPageParams.getCount());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.UserService#findUserFollows(com.yatu.data.UserPageParams)
   */
  @Override
  @Transactional(readOnly = true)
  public List<User> findUserFollows(UserPageParams userPageParams) throws InvalidParamsException {
    Integer isExisted = userRepository.isExistedUserById(userPageParams.getUid());

    if (isExisted.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.USER_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    return userRepository.findUserFollow(userPageParams.getUid(), userPageParams.getStart(), userPageParams.getCount());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.UserService#findUserWorksByPage(com.yatu.data.UserPageParams)
   */
  @Override
  @Transactional(readOnly = true)
  public List<Works> findUserWorksByPage(UserPageParams userPageParams) throws InvalidParamsException {
    Integer isExisted = userRepository.isExistedUserById(userPageParams.getUid());

    if (isExisted.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.USER_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    List<Works> list = worksRepository.findWorksByUid(userPageParams.getUid(), userPageParams.getStart(),
        userPageParams.getCount());

    if ((list != null) && (list.size() > 0)) {
      List<String> wids = new ArrayList<>();

      for (Works works : list) {
        wids.add(works.getId());
      }

      List<String> result = worksRepository.findWoksIsLiked(wids);

      if (result != null) {
        for (String s : result) {
          for (Works works : list) {
            if (s.equals(works.getId())) {
              works.setLiked(1);

              break;
            }
          }
        }
      }
    }

    return list;
  } // end method findUserWorksByPage

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.UserService#followUser(java.lang.String, java.lang.String, int)
   */
  @Override @Transactional public void followUser(String uid, String followUid, int followed)
    throws InvalidParamsException {
    Integer isExisted = userRepository.isExistedUserById(followUid);

    if (isExisted.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.USER_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    List<String> result = userRepository.isFollowed(uid, followUid);

    userRepository.followUser((result.isEmpty()) ? false : true, followed, uid, followUid);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.UserService#isFollowedUser(java.lang.String, java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public boolean isFollowedUser(String uid, String followUid) throws InvalidParamsException {
    Integer isExisted = userRepository.isExistedUserById(followUid);

    if (isExisted.intValue() == 0) {
      throw new InvalidParamsException(ErrorCode.USER_NOT_FOUND.DESC(), ErrorCode.NOT_FOUND,
        HttpStatus.NOT_FOUND);
    }

    List<String> result = userRepository.isFollowed(uid, followUid);

    if (result.isEmpty()) {
      return false;
    }

    if (result.get(0).equals("0")) {
      return false;
    }

    return true;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  com.yatu.service.UserService#validateUserToken(java.lang.String, java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public Integer validateUserToken(String uid, String token) {
    return userRepository.validateUserToken(uid, token);
  }
} // end class UserServiceImpl
