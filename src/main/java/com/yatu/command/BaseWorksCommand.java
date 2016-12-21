package com.yatu.command;

import com.yatu.domain.Works;

/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 11:40
 */
public class BaseWorksCommand {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String contentUrl;

  private long    createTime;
  private String eid;

  private int height;

  private String thumbnail;

  private int type;

  private int width;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new BaseWorksCommand object.
   */
  public BaseWorksCommand() { }

  public BaseWorksCommand(Works works) {
    this.eid = works.getId();
    this.contentUrl = works.getContentUrl();
    this.thumbnail = works.getThumbnail();
    this.width = works.getWidth();
    this.height = works.getHeight();
    this.type = works.getType();
    this.createTime = works.getCreateDate().getTime();
  }

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
   * getter method for create time.
   *
   * @return  int
   */
  public long getCreateTime() {
    return createTime;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

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
   * getter method for height.
   *
   * @return  String
   */
  public int getHeight() {
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
   * @return  int
   */
  public int getType() {
    return type;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for width.
   *
   * @return  String
   */
  public int getWidth() {
    return width;
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
   * setter method for create time.
   *
   * @param  createTime  int
   */
  public void setCreateTime(long createTime) {
    this.createTime = createTime;
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
   * setter method for height.
   *
   * @param  height  String
   */
  public void setHeight(int height) {
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
   * @param  type  int
   */
  public void setType(int type) {
    this.type = type;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for width.
   *
   * @param  width  String
   */
  public void setWidth(int width) {
    this.width = width;
  }
} // end class BaseWorksCommand
