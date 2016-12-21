package com.yatu.data;


import org.hibernate.validator.constraints.NotBlank;

import org.springframework.http.HttpStatus;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.UserRepository;

import com.yatu.service.UserService;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;


/**
 * Created by hjf_mac on 9/20/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 09:51
 */
public class BaseParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "The app key",
    required = true,
    example  = "q87sdfskdhfksdjf343"
  )
  @NotBlank(message = "appKey is required")
  private String appKey;

  @ApiModelProperty(
    value    = "The device",
    required = true,
    example  = "IOS"
  )
  @NotBlank(message = "device is required")
  private String device;

  @ApiModelProperty(
    value    = "The sign",
    required = true,
    example  = "iyd9e8rteokcvb"
  )
  @NotBlank(message = "sign is required")
  private String sign;

  @ApiModelProperty private String token;
  @ApiModelProperty private String userid;

  @ApiModelProperty(
    value    = "The unique uuid",
    required = true,
    example  = "dfkghdfj34jsdf"
  )
  @NotBlank(message = "uuid is required")
  private String uuid;

  @ApiModelProperty(
    value    = "The app version",
    required = true,
    example  = "1.0"
  )
  @NotBlank(message = "version is required")
  private String version;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for app key.
   *
   * @return  String
   */
  public String getAppKey() {
    return appKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for device.
   *
   * @return  String
   */
  public String getDevice() {
    return device;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for sign.
   *
   * @return  String
   */
  public String getSign() {
    return sign;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for token.
   *
   * @return  String
   */
  public String getToken() {
    return token;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for userid.
   *
   * @return  String
   */
  public String getUserid() {
    return userid;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for uuid.
   *
   * @return  String
   */
  public String getUuid() {
    return uuid;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for version.
   *
   * @return  String
   */
  public String getVersion() {
    return version;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for app key.
   *
   * @param  appKey  String
   */
  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for device.
   *
   * @param  device  String
   */
  public void setDevice(String device) {
    this.device = device;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for sign.
   *
   * @param  sign  String
   */
  public void setSign(String sign) {
    this.sign = sign;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for token.
   *
   * @param  token  String
   */
  public void setToken(String token) {
    this.token = token;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for userid.
   *
   * @param  userid  String
   */
  public void setUserid(String userid) {
    this.userid = userid;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for uuid.
   *
   * @param  uuid  String
   */
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for version.
   *
   * @param  version  String
   */
  public void setVersion(String version) {
    this.version = version;
  }

} // end class BaseParams
