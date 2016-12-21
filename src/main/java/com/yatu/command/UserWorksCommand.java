package com.yatu.command;

import com.yatu.domain.Works;


/**
 * Created by hjf_mac on 9/27/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/27/2016 19:30
 */
public class UserWorksCommand extends BaseWorksCommand {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private int liked;

  //~ Constructors -----------------------------------------------------------------------------------------------------


  /**
   * Creates a new UserWorksCommand object.
   */
  public UserWorksCommand() { }

  /**
   * Creates a new UserWorksCommand object.
   *
   * @param  works  Works
   */
  public UserWorksCommand(Works works) {
    super(works);
    this.liked = works.getLiked();
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

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
   * setter method for liked.
   *
   * @param  liked  int
   */
  public void setLiked(int liked) {
    this.liked = liked;
  }
} // end class UserWorksCommand
