package com.yatu.command;

import com.yatu.domain.Works;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 11:45
 */
public class BaseWorksRespCommand {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private BaseWorksCommand exp;

  private int liked;

  private BaseUserCommand user;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new BaseWorksRespCommand object.
   */
  public BaseWorksRespCommand() { }

  /**
   * Creates a new BaseWorksRespCommand object.
   *
   * @param  works  Works
   */
  public BaseWorksRespCommand(Works works) {
    this.liked = works.getLiked();
    this.exp   = new BaseWorksCommand(works);
    this.user  = new BaseUserCommand(works.getUser());
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for exp.
   *
   * @return  BaseWorksCommand
   */
  public BaseWorksCommand getExp() {
    return exp;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for liked.
   *
   * @return  int
   */
  public int getLiked() {
    return liked;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for user.
   *
   * @return  BaseUserCommand
   */
  public BaseUserCommand getUser() {
    return user;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for exp.
   *
   * @param  exp  BaseWorksCommand
   */
  public void setExp(BaseWorksCommand exp) {
    this.exp = exp;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for liked.
   *
   * @param  liked  int
   */
  public void setLiked(int liked) {
    this.liked = liked;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for user.
   *
   * @param  user  BaseUserCommand
   */
  public void setUser(BaseUserCommand user) {
    this.user = user;
  }
} // end class BaseWorksRespCommand
