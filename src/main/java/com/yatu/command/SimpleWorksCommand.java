package com.yatu.command;

import com.yatu.domain.User;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 11:40
 */
public class SimpleWorksCommand {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String eid;

  private String thumbnail;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new BaseWorksCommand object.
   */
  public SimpleWorksCommand() { }

  public SimpleWorksCommand(User user) {
    this.eid = user.getWorks().getId();
    this.thumbnail = user.getWorks().getThumbnail();
  }

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
   * getter method for thumbnail.
   *
   * @return  String
   */
  public String getThumbnail() {
    return thumbnail;
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
   * setter method for thumbnail.
   *
   * @param  thumbnail  String
   */
  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

} // end class SimpleWorksCommand
