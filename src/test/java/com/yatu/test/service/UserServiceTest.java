package com.yatu.test.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import static org.mockito.BDDMockito.given;

import static org.mockito.Matchers.anyString;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yatu.data.UserPageParams;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.UserRepository;
import com.yatu.repository.WorksRepository;

import com.yatu.service.UserService;
import com.yatu.service.impl.UserServiceImpl;


/**
 * Created by hjf_mac on 10/11/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/11/2016 11:08
 */
public class UserServiceTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Rule public ExpectedException thrown = ExpectedException.none();

  @Mock private UserRepository userRepository;

  private UserService userService;

  @Mock private WorksRepository worksRepository;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * setup.
   */
  @Before public void setup() {
    MockitoAnnotations.initMocks(this);
    this.userService = new UserServiceImpl(this.userRepository, this.worksRepository);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindUserById.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindUserById() throws Exception {
    given(this.userRepository.isExistedUserById(anyString())).willReturn(0);
    thrown.expect(InvalidParamsException.class);
    thrown.expectMessage("User not found with the id.");
    this.userService.findUserById("1");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindUserFans.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindUserFans() throws Exception {
    given(this.userRepository.isExistedUserById(anyString())).willReturn(0);
    thrown.expect(InvalidParamsException.class);
    thrown.expectMessage("User not found with the id.");
    this.userService.findUserFans(new UserPageParams());
  }


} // end class UserServiceTest
