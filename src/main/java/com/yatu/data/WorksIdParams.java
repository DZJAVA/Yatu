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
@ApiModel public class WorksIdParams extends BaseParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "The design id",
    required = true,
    example  = "1"
  )
  @NotBlank(message = "eid is required.")
  private String eid;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for eid.
   *
   * @return  Integer
   */
  public String getEid() {
    return eid;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for eid.
   *
   * @param  eid  Integer
   */
  public void setEid(String eid) {
    this.eid = eid;
  }
} // end class WorksIdParams
