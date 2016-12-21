package com.yatu.test.repository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import com.yatu.test.BaseRepositoryTest;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.yatu.data.UserParams;

import com.yatu.domain.User;

import com.yatu.repository.UserRepository;

import com.yatu.test.util.FileUtil;

import com.yatu.util.UUIDGenerator;


/**
 * Created by hjf_mac on 10/11/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/11/2016 14:52
 */
public class UserRepositoryTest extends BaseRepositoryTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Autowired UserRepository userRepository;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * testalidateUserToken.
   *
   * @throws  Exception  exception
   */
  @Test public void testalidateUserToken() throws Exception {
    Integer result = this.userRepository.validateUserToken("1", "1");

    assertThat(result).isGreaterThan(0);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testCreateUser.
   *
   * @throws  Exception  exception
   */
  @Test public void testCreateUser() throws Exception {
    String     content    = FileUtil.getFileContent("json/createUserParams.json", null);
    UserParams userParams = this.objectMapper.readValue(content, UserParams.class);
    User       user       = userParams.populate();
    user.setId(UUIDGenerator.getUUID());

    User user1 = this.userRepository.createUser(user);

    List<User> result = this.userRepository.findUserById(user1.getId());

    assertThat(user1).isNotNull();
    assertThat(result).isNotEmpty();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindUserFans.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindUserFans() throws Exception {
    List<User> result = this.userRepository.findUserFans("1", 0, 10);

    assertThat(result).isNotEmpty();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindUserFansCount.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindUserFansCount() throws Exception {
    Integer result = this.userRepository.findUserFansCount("1");

    assertThat(result.intValue()).isGreaterThan(0);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindUserFollow.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindUserFollow() throws Exception {
    List<User> result = this.userRepository.findUserFollow("1", 0, 10);

    assertThat(result).isNotEmpty();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testIsExistedUserById.
   *
   * @throws  Exception  exception
   */
  @Test public void testIsExistedUserById() throws Exception {
    Integer result = this.userRepository.isExistedUserById("1");

    assertThat(result.intValue()).isGreaterThan(0);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testIsExistedUserByName.
   *
   * @throws  Exception  exception
   */
  @Test public void testIsExistedUserByName() throws Exception {
    List<String> result = this.userRepository.isExistedUserByUsername("1");

    assertThat(result).isNotEmpty();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testIsFollowed.
   *
   * @throws  Exception  exception
   */
  @Test public void testIsFollowed() throws Exception {
    List<String> result = this.userRepository.isFollowed("1", "2");

    assertThat(result).isNotEmpty();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testUpdateUser.
   *
   * @throws  Exception  exception
   */
  @Test public void testUpdateUser() throws Exception {
    String     content    = FileUtil.getFileContent("json/createUserParams.json", null);
    UserParams userParams = this.objectMapper.readValue(content, UserParams.class);
    User       user       = userParams.populate();
    user.setUsername("3");

    User user1 = this.userRepository.updateUser(user);

    List<User> result = this.userRepository.findUserById("3");

    assertThat(user1).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result.get(0).getNickname()).isEqualTo(user1.getNickname());
  }


} // end class UserRepositoryTest
