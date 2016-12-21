package com.yatu.domain;


/**
 * Created by hjf_mac on 9/20/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/20/2016 15:42
 */
public class User extends BaseDomain {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String avatar;
  private int    fansCount;
  private int    followCount;

  private String  id;
  private String  nickname;
  private String  password;
  private Integer sex;
  private String  token;
  private Integer type;
  private String  username;
  private Works   works;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for avatar.
   *
   * @return  String
   */
  public String getAvatar() {
    return avatar;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for fans count.
   *
   * @return  int
   */
  public int getFansCount() {
    return fansCount;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for follow count.
   *
   * @return  int
   */
  public int getFollowCount() {
    return followCount;
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
   * getter method for nickname.
   *
   * @return  String
   */
  public String getNickname() {
    return nickname;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for password.
   *
   * @return  String
   */
  public String getPassword() {
    return password;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for sex.
   *
   * @return  int
   */
  public Integer getSex() {
    return sex;
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
   * getter method for type.
   *
   * @return  Integer
   */
  public Integer getType() {
    return type;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for username.
   *
   * @return  String
   */
  public String getUsername() {
    return username;
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
   * setter method for avatar.
   *
   * @param  avatar  String
   */
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for fans count.
   *
   * @param  fansCount  int
   */
  public void setFansCount(int fansCount) {
    this.fansCount = fansCount;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for follow count.
   *
   * @param  followCount  int
   */
  public void setFollowCount(int followCount) {
    this.followCount = followCount;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for followers.
   *
   * <p>//~
   * ------------------------------------------------------------------------------------------------------------------
   * /** setter method for id.</p>
   *
   * @param  id  String
   */
  public void setId(String id) {
    this.id = id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for nickname.
   *
   * @param  nickname  String
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for password.
   *
   * @param  password  String
   */
  public void setPassword(String password) {
    this.password = password;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for sex.
   *
   * @param  sex  int
   */
  public void setSex(Integer sex) {
    this.sex = sex;
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
   * setter method for type.
   *
   * @param  type  Integer
   */
  public void setType(Integer type) {
    this.type = type;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for username.
   *
   * @param  username  String
   */
  public void setUsername(String username) {
    this.username = username;
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

} // end class User
