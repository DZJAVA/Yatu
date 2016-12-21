package com.yatu.command;

import java.util.ArrayList;
import java.util.List;

import com.yatu.domain.User;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 11:37
 */
public class UserCommand extends BaseUserCommand {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private List<SimpleWorksCommand> exps      = new ArrayList<>();
  private int                      fansCount;

  private int followCount;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new BaseUserCommand object.
   */
  public UserCommand() { }

  /**
   * Creates a new BaseUserCommand object.
   *
   * @param  users  User
   */
  public UserCommand(List<User> users) {
    super(users.get(0));

    User user = users.get(0);
    this.fansCount   = user.getFansCount();
    this.followCount = user.getFollowCount();

    for (User u : users) {
      if (u.getWorks() == null) {
        break;
      }

      this.exps.add(new SimpleWorksCommand(u));
    }
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for exps.
   *
   * @return  List
   */
  public List<SimpleWorksCommand> getExps() {
    return exps;
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
   * setter method for exps.
   *
   * @param  exps  List
   */
  public void setExps(List<SimpleWorksCommand> exps) {
    this.exps = exps;
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


} // end class BaseUserCommand
