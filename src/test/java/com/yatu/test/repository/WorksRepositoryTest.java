package com.yatu.test.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertTrue;

import com.yatu.test.BaseRepositoryTest;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yatu.data.PageParams;
import com.yatu.data.WorksParams;

import com.yatu.domain.Works;

import com.yatu.repository.WorksRepository;

import com.yatu.test.util.FileUtil;

import com.yatu.util.UUIDGenerator;


/**
 * Created by hjf_mac on 10/11/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/11/2016 14:52
 */
public class WorksRepositoryTest extends BaseRepositoryTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Autowired WorksRepository worksRepository;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * testCreateWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testCreateWorks() throws Exception {
    String      content     = FileUtil.getFileContent("json/createWorksParams.json", null);
    WorksParams worksParams = this.objectMapper.readValue(content, WorksParams.class);
    Works       works       = worksParams.populate();
    works.setId(UUIDGenerator.getUUID());

    Works works1 = this.worksRepository.createWorks(works);

    assertThat(works1).isNotNull();
    assertThat(works1.getId()).isEqualTo(works.getId());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testExistedWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testExistedWorks() throws Exception {
    Integer count = this.worksRepository.isExistedWorks("1");

    assertThat(count.intValue()).isEqualTo(1);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindUserLikedWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindUserLikedWorks() throws Exception {
    List wids = new ArrayList<>();
    wids.add("1");
    wids.add("2");

    List<Map<String, Object>> list = this.worksRepository.findUserLikedWorks("1", wids);

    assertThat(list).isNotEmpty();
    assertTrue("1,2".contains(list.get(0).get("worksId").toString()));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindWoksIsLiked.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindWoksIsLiked() throws Exception {
    List wids = new ArrayList<>();
    wids.add("1");
    wids.add("2");

    List<String> list = this.worksRepository.findWoksIsLiked(wids);

    assertThat(list).isNotEmpty();
    assertTrue("1,2".contains(list.get(0)));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindWorksById.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindWorksById() throws Exception {
    List<Works> list = this.worksRepository.findWorksById("1");

    assertThat(list).isNotEmpty();
    assertThat(list.get(0).getId()).isEqualTo("1");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testFindWorksByUid.
   *
   * @throws  Exception  exception
   */
  @Test public void testFindWorksByUid() throws Exception {
    List<Works> list = this.worksRepository.findWorksByUid("1", 0, 5);

    assertThat(list).isNotEmpty();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testIsLikedIWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testIsLikedIWorks() throws Exception {
    List<String> result = this.worksRepository.isLikedWorks("1", "1");

    assertThat(result).isNotEmpty();
    assertThat(result.size()).isEqualTo(1);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNotExistedWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNotExistedWorks() throws Exception {
    Integer count = this.worksRepository.isExistedWorks("aqweqsds");

    assertThat(count.intValue()).isEqualTo(0);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testNullIsLikedIWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testNullIsLikedIWorks() throws Exception {
    List<String> result = this.worksRepository.isLikedWorks("3", "1");

    assertThat(result).isEmpty();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testindPublicWorks.
   *
   * @throws  Exception  exception
   */
  @Test public void testPublicWorks() throws Exception {
    PageParams pageParams = new PageParams();
    pageParams.setStart(0);
    pageParams.setCount(10);

    List<Works> list = this.worksRepository.findPublicWorks(pageParams);

    assertThat(list).isNotEmpty();
  }


} // end class WorksRepositoryTest
