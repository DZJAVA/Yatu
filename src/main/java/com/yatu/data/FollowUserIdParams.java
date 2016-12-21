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
@ApiModel public class FollowUserIdParams extends BaseParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "The user id",
    required = true,
    example  = "1"
  )
  @NotBlank(message = "followUId is required.")
  private String followUId;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for follow UId.
   *
   * @return  String
   */
  public String getFollowUId() {
    return followUId;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for follow UId.
   *
   * @param  followUId  String
   */
  public void setFollowUId(String followUId) {
    this.followUId = followUId;
  }
} // end class UserIdParams
