package com.yatu.data;

import com.yatu.domain.User;
import com.yatu.domain.Works;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


/**
 * Created by hjf_mac on 9/21/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/21/2016 10:52
 */
@ApiModel public class WorksParams extends BaseParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "The Design contentUrl",
    required = true,
    example  = "www.asdsadas.jpg"
  )
  private String contentUrl;

  @ApiModelProperty(
    value    = "The Design height",
    required = true,
    example  = "120"
  )
  @NotNull(message = "height is required.")
  private Integer height;

  @ApiModelProperty(
    value    = "The Design thumbnail",
    required = true,
    example  = "www.asdsadas.jpg"
  )
  @NotBlank(message = "thumbnail is required.")
  private String thumbnail;

  @ApiModelProperty(
    value    = "The Design type",
    required = true,
    example  = "1"
  )
  @NotNull(message = "type is required.")
  private Integer type;

  @ApiModelProperty(
    value    = "The Design width",
    required = true,
    example  = "120"
  )
  @NotNull(message = "width is required.")
  private Integer width;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for content url.
   *
   * @return  String
   */
  public String getContentUrl() {
    return contentUrl;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for height.
   *
   * @return  Integer
   */
  public Integer getHeight() {
    return height;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for thumbnail.
   *
   * @return  String
   */
  public String getThumbnail() {
    return thumbnail;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for type.
   *
   * @return  Integer
   */
  public Integer getType() {
    return type;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for width.
   *
   * @return  Integer
   */
  public Integer getWidth() {
    return width;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * populate.
   *
   * @return  Works
   */
  public Works populate() {
    Works works = new Works();
    works.setThumbnail(this.thumbnail);
    works.setWidth(this.width);
    works.setHeight(this.height);
    works.setType(this.type);
    works.setContentUrl(this.contentUrl);
    User user = new User();
    user.setId(this.getUserid());
    works.setUser(user);

    return works;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for content url.
   *
   * @param  contentUrl  String
   */
  public void setContentUrl(String contentUrl) {
    this.contentUrl = contentUrl;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for height.
   *
   * @param  height  Integer
   */
  public void setHeight(Integer height) {
    this.height = height;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for thumbnail.
   *
   * @param  thumbnail  String
   */
  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for type.
   *
   * @param  type  Integer
   */
  public void setType(Integer type) {
    this.type = type;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for width.
   *
   * @param  width  Integer
   */
  public void setWidth(Integer width) {
    this.width = width;
  }
} // end class WorksParams
