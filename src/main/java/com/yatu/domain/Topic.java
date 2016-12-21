package com.yatu.domain;


/**
 * Created by hjf_mac on 9/20/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/20/2016 15:50
 */
public class Topic extends BaseDomain {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private int attentionCount;

  private String content;

  private User createUser;

  private int expsCount;

  private String id;

  private String images;

  private String title;

  private Works works;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for attention count.
   *
   * @return  int
   */
  public int getAttentionCount() {
    return attentionCount;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for content.
   *
   * @return  String
   */
  public String getContent() {
    return content;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for create user.
   *
   * @return  User
   */
  public User getCreateUser() {
    return createUser;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for exps count.
   *
   * @return  int
   */
  public int getExpsCount() {
    return expsCount;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for id.
   *
   * @return  String
   */
  public String getId() {
    return id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for images.
   *
   * @return  String
   */
  public String getImages() {
    return images;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for title.
   *
   * @return  String
   */
  public String getTitle() {
    return title;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for works.
   *
   * @return  Works
   */
  public Works getWorks() {
    return works;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for attention count.
   *
   * @param  attentionCount  int
   */
  public void setAttentionCount(int attentionCount) {
    this.attentionCount = attentionCount;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for content.
   *
   * @param  content  String
   */
  public void setContent(String content) {
    this.content = content;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for create user.
   *
   * @param  createUser  User
   */
  public void setCreateUser(User createUser) {
    this.createUser = createUser;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for exps count.
   *
   * @param  expsCount  int
   */
  public void setExpsCount(int expsCount) {
    this.expsCount = expsCount;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for id.
   *
   * @param  id  String
   */
  public void setId(String id) {
    this.id = id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for images.
   *
   * @param  images  String
   */
  public void setImages(String images) {
    this.images = images;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for title.
   *
   * @param  title  String
   */
  public void setTitle(String title) {
    this.title = title;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for works.
   *
   * @param  works  Works
   */
  public void setWorks(Works works) {
    this.works = works;
  }

} // end class Topic
