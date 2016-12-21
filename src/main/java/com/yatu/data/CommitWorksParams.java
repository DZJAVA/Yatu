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
@ApiModel public class CommitWorksParams extends BaseParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
    value    = "The design id",
    required = true,
    example  = "1"
  )
  @NotBlank(message = "eid is required.")
  private String eid;

  @ApiModelProperty(
    value    = "The topic id",
    required = true,
    example  = "1"
  )
  @NotBlank(message = "tid is required.")
  private String tid;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for eid.
   *
   * @return  String
   */
  public String getEid() {
    return eid;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for tid.
   *
   * @return  String
   */
  public String getTid() {
    return tid;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for eid.
   *
   * @param  eid  String
   */
  public void setEid(String eid) {
    this.eid = eid;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for tid.
   *
   * @param  tid  String
   */
  public void setTid(String tid) {
    this.tid = tid;
  }
} // end class CommitWorksParams
