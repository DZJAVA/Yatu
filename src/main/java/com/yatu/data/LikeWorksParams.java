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
@ApiModel public class LikeWorksParams extends WorksIdParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "Like the design or not.",
    required = true,
    example  = "true"
  )
  @NotNull(message = "liked status is required.")
  private Boolean liked;

  public Boolean getLiked() {
    return liked;
  }

  public void setLiked(Boolean liked) {
    this.liked = liked;
  }
} // end class WorksIdParams
