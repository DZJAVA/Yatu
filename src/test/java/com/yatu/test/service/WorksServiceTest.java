package com.yatu.test.service;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import static org.mockito.BDDMockito.given;

import static org.mockito.Matchers.anyString;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yatu.data.UserPageParams;

import com.yatu.domain.Works;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.UserRepository;
import com.yatu.repository.WorksRepository;

import com.yatu.service.UserService;
import com.yatu.service.WorksService;
import com.yatu.service.impl.UserServiceImpl;
import com.yatu.service.impl.WorksServiceImpl;


/**
 * Created by hjf_mac on 10/11/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/11/2016 11:08
 */
public class WorksServiceTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Rule public ExpectedException thrown = ExpectedException.none();

  @Mock private WorksRepository worksRepository;

  private WorksService worksService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * setup.
   */
  @Before public void setup() {
    MockitoAnnotations.initMocks(this);
    this.worksService = new WorksServiceImpl(this.worksRepository);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testEmptyListFindWorksById.
   *
   * @throws  Exception  exception
   */
  @Test public void testEmptyListFindWorksById() throws Exception {
    given(this.worksRepository.findWorksById(anyString())).willReturn(new ArrayList<Works>());
    thrown.expect(InvalidParamsException.class);
    thrown.expectMessage("Design not found with the id.");
    this.worksService.findWorksById("1");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testInvalidWid.
   *
   * @throws  Exception  exception
   */
  @Test public void testInvalidWid() throws Exception {
    given(this.worksRepository.isExistedWorks(anyString())).willReturn(0);
    thrown.expect(InvalidParamsException.class);
    thrown.expectMessage("Design not found with the id.");
    this.worksService.isLikedWorks("1", "1");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullListFindWorksById.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullListFindWorksById() throws Exception {
    given(this.worksRepository.findWorksById(anyString())).willReturn(null);
    thrown.expect(InvalidParamsException.class);
    thrown.expectMessage("Design not found with the id.");
    this.worksService.findWorksById("1");
  }

} // end class WorksServiceTest
