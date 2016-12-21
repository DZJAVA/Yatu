package com.yatu.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


/**
 * Created by hjf_mac on 9/21/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/21/2016 10:52
 */
@ApiModel public class FollowUserParams extends FollowUserIdParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "The follow status",
    required = true,
    example  = "true"
  )
  @NotNull(message = "followed is required.")
  private Boolean followed;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for followed.
   *
   * @return  Boolean
   */
  public Boolean getFollowed() {
    return followed;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for followed.
   *
   * @param  followed  Boolean
   */
  public void setFollowed(Boolean followed) {
    this.followed = followed;
  }
} // end class FollowUserParams
