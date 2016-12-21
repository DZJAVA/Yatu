package com.yatu.command;

import com.yatu.domain.User;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 11:37
 */
public class BaseUserCommand {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String avatar;

  private String name;

  private String uid;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new BaseUserCommand object.
   */
  public BaseUserCommand() { }

  /**
   * Creates a new BaseUserCommand object.
   *
   * @param  user  User
   */
  public BaseUserCommand(User user) {
    this.avatar = user.getAvatar();
    this.name   = user.getNickname();
    this.uid    = user.getId();
  }

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
   * getter method for name.
   *
   * @return  String
   */
  public String getName() {
    return name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

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
   * setter method for avatar.
   *
   * @param  avatar  String
   */
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for name.
   *
   * @param  name  String
   */
  public void setName(String name) {
    this.name = name;
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
} // end class BaseUserCommand
