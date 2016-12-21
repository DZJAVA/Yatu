package com.yatu.data;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Created by hjf_mac on 9/21/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/21/2016 10:52
 */
@ApiModel public class UserIdParams extends BaseParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "The user id",
    required = true,
    example  = "1"
  )
  @NotBlank(message = "uid is required.")
  private String uid;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for uid.
   *
   * @return  String
   */
  public String getUid() {
    return uid;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for uid.
   *
   * @param  uid  String
   */
  public void setUid(String uid) {
    this.uid = uid;
  }
} // end class UserIdParams
