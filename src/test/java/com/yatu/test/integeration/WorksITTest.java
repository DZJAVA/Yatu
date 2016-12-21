package com.yatu.test.integeration;

import java.io.IOException;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import com.yatu.data.LikeWorksParams;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.springframework.http.ResponseEntity;

import org.springframework.util.StringUtils;

import com.yatu.data.ResponseInfo;
import com.yatu.data.WorksIdParams;

import com.yatu.test.BaseIntegerationTest;
import com.yatu.test.util.FileUtil;


/**
 * Created by hjf_mac on 10/8/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/08/2016 14:46
 */
public class WorksITTest extends BaseIntegerationTest {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * testCreateDesign.
   *
   * @throws  IOException  exception
   */
  @Test public void testCreateDesign() throws IOException {
    String content = FileUtil.getFileContent("json/createWorksParams.json", "");

    ResponseEntity<ResponseInfo> responseEntity = post("/design/createDesign", content, ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(StringUtils.hasText(responseInfo.getData().get("eid")));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testGetDesignDetailById.
   *
   * @throws  IOException  exception
   */
  @Test public void testGetDesignDetailById() throws IOException {
    String        content       = FileUtil.getFileContent("json/baseParams.json", "");
    WorksIdParams worksIdParams = objectMapper.readValue(content, WorksIdParams.class);
    worksIdParams.setEid("1");

    ResponseEntity<ResponseInfo> responseEntity = post("/design/getDesignDetailById",
        objectMapper.writeValueAsString(worksIdParams), ResponseInfo.class);

    ResponseInfo<Map<String, String>> responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * test.
   *
   * @throws  IOException  exception
   */
  @Test public void testGetPublicDesignsList() throws IOException {
    String content = FileUtil.getFileContent("json/baseParams.json", "");

    ResponseEntity<ResponseInfo> responseEntity = post("/design/getPublicDesigns", content, ResponseInfo.class);

    ResponseInfo responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testIsLikedDesign.
   *
   * @throws  IOException  exception
   */
  @Test public void testIsLikedDesign() throws IOException {
    String        content       = FileUtil.getFileContent("json/baseParams.json", "");
    WorksIdParams worksIdParams = objectMapper.readValue(content, WorksIdParams.class);
    worksIdParams.setEid("1");

    ResponseEntity<ResponseInfo> responseEntity = post("/design/isLikedDesign",
        objectMapper.writeValueAsString(worksIdParams), ResponseInfo.class);

    ResponseInfo responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * testLikeDesign.
   *
   * @throws  IOException  exception
   */
  @Test public void testLikeDesign() throws IOException {
    String        content       = FileUtil.getFileContent("json/baseParams.json", "");
    LikeWorksParams likeWorksParams = objectMapper.readValue(content, LikeWorksParams.class);
    likeWorksParams.setEid("1");
    likeWorksParams.setLiked(true);

    ResponseEntity<ResponseInfo> responseEntity = post("/design/likeDesign",
        objectMapper.writeValueAsString(likeWorksParams), ResponseInfo.class);

    ResponseInfo responseInfo = responseEntity.getBody();
    assertTrue(responseInfo.getState() == 200);
  }
} // end class WorksITTest
