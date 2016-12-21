package com.yatu.command;

import com.yatu.domain.Works;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 11:45
 */
public class SingleWorksRespCommand extends BaseWorksRespCommand {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private Map social = new HashMap<>();

  public SingleWorksRespCommand() {

  }

  public SingleWorksRespCommand(List<Works> workses, String userId) {
    super(workses.get(0));
    int likedCount = 0;
    int currentUserLiked = 0;
    List<BaseUserCommand> list = new ArrayList<>();
    for (Works w : workses) {
      if (w.getLikedUser() == null) {
        break;
      }
      if (w.getLiked() > 0) {
        likedCount ++;
        BaseUserCommand bc = new BaseUserCommand(w.getLikedUser());
        list.add(bc);
        if (w.getLikedUser().getId().equals(userId)) {
          currentUserLiked = 1;
        }
      }
    }
    this.setLiked(currentUserLiked);
    this.social.put("likedCount", likedCount);
    this.social.put("likedUsers", list);
  }

  public Map getSocial() {
    return social;
  }

  public void setSocial(Map social) {
    this.social = social;
  }
} // end class BaseWorksRespCommand
