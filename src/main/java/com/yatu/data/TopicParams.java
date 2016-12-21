package com.yatu.data;

import com.yatu.domain.User;
import org.hibernate.validator.constraints.NotBlank;

import com.yatu.domain.Topic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Created by hjf_mac on 9/21/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/21/2016 10:52
 */
@ApiModel public class TopicParams extends BaseParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "The Topic content",
    required = true,
    example  = "test content"
  )
  @NotBlank(message = "content is required.")
  private String content;

  @ApiModelProperty(
    value   = "The Topic images",
    example = "[\"qweqwe.jpg\", \"qweqeqw.gif\"]"
  )
  private String[] images;

  @ApiModelProperty(
    value    = "The Topic title",
    required = true,
    example  = "test topic"
  )
  @NotBlank(message = "title is required.")
  private String title;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for content.
   *
   * @return  String
   */
  public String getContent() {
    return content;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for images.
   *
   * @return  String[]
   */
  public String[] getImages() {
    return images;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for title.
   *
   * @return  String
   */
  public String getTitle() {
    return title;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * populate.
   *
   * @return  Topic
   */
  public Topic populate() {
    Topic topic = new Topic();
    topic.setTitle(this.title);
    topic.setContent(this.content);

    if ((this.images != null) && (this.images.length > 0)) {
      StringBuilder sb = new StringBuilder();

      for (String str : this.images) {
        sb.append(str).append(",");
      }

      sb.deleteCharAt(sb.length() - 1);
      topic.setImages(sb.toString());
    }
    User user = new User();
    user.setId(this.getUserid());
    topic.setCreateUser(user);

    return topic;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for content.
   *
   * @param  content  String
   */
  public void setContent(String content) {
    this.content = content;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for images.
   *
   * @param  images  String[]
   */
  public void setImages(String[] images) {
    this.images = images;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for title.
   *
   * @param  title  String
   */
  public void setTitle(String title) {
    this.title = title;
  }
} // end class TopicParams
