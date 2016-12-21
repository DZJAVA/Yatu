package com.yatu.data;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import org.springframework.util.DigestUtils;

import com.yatu.domain.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Created by hjf_mac on 9/21/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/21/2016 10:52
 */
@ApiModel public class UserParams extends BaseParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "The user avatar",
    required = true,
    example  = "asdqweqweqwsdf"
  )
  @NotBlank(message = "avatar is required.")
  private String avatar;

  @ApiModelProperty(
    value    = "The user nickname",
    required = true,
    example  = "asdqweqweqwsdf"
  )
  @NotBlank(message = "name is required.")
  private String name;

  @ApiModelProperty(
    value    = "The user openId",
    required = true,
    example  = "asdqweqweqwsdf"
  )
  @NotBlank(message = "openId is required.")
  private String openId;

  @ApiModelProperty(
    value    = "The user login token",
    required = true,
    example  = "asdqweqweqwsdf"
  )
  @NotBlank(message = "token is required.")
  private String openToken;

  @ApiModelProperty(
    value   = "The user sex",
    example = "0"
  )
  private Integer sex;

  @ApiModelProperty(
    value    = "The user login type",
    required = true,
    example  = "0"
  )
  @NotNull(message = "type is required.")
  private Integer type;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for avatar.
   *
   * @return  String
   */
  public String getAvatar() {
    return avatar;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for name.
   *
   * @return  String
   */
  public String getName() {
    return name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for open id.
   *
   * @return  String
   */
  public String getOpenId() {
    return openId;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for open token.
   *
   * @return  String
   */
  public String getOpenToken() {
    return openToken;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for sex.
   *
   * @return  Integer
   */
  public Integer getSex() {
    return sex;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for type.
   *
   * @return  String
   */
  public Integer getType() {
    return type;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * populate.
   *
   * @return  User
   */
  public User populate() {
    User user = new User();
    user.setUsername(this.openId);
    user.setNickname(this.name);
    user.setAvatar(this.avatar);
    user.setSex(this.sex);
    user.setType(this.type);
    user.setToken(this.getOpenToken());
    user.setPassword(DigestUtils.md5DigestAsHex(this.openToken.getBytes()));

    return user;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for avatar.
   *
   * @param  avatar  String
   */
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for name.
   *
   * @param  name  String
   */
  public void setName(String name) {
    this.name = name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for open id.
   *
   * @param  openId  String
   */
  public void setOpenId(String openId) {
    this.openId = openId;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for open token.
   *
   * @param  openToken  String
   */
  public void setOpenToken(String openToken) {
    this.openToken = openToken;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for sex.
   *
   * @param  sex  Integer
   */
  public void setSex(Integer sex) {
    this.sex = sex;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for type.
   *
   * @param  type  String
   */
  public void setType(Integer type) {
    this.type = type;
  }
} // end class UserParams
