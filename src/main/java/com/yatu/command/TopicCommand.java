package com.yatu.command;

import com.yatu.domain.Topic;
import org.springframework.util.StringUtils;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 11:37
 */
public class TopicCommand {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private int attentionCount;

  private String content;

  private int expsCount;

  private String[] images;
  private String   tid;

  private String title;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new TopicCommand object.
   */
  public TopicCommand() { }

  /**
   * Creates a new TopicCommand object.
   *
   * @param  topic  Topic
   */
  public TopicCommand(Topic topic) {
    this.tid            = topic.getId();
    this.title          = topic.getTitle();
    this.content        = topic.getContent();
    if (StringUtils.hasText(topic.getImages())) {
      this.images         = topic.getImages().split(",");
    }
    this.expsCount      = topic.getExpsCount();
    this.attentionCount = topic.getAttentionCount();
  }

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
   * getter method for exps count.
   *
   * @return  int
   */
  public int getExpsCount() {
    return expsCount;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for images.
   *
   * @return  String[]
   */
  public String[] getImages() {
    return images;
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
   * getter method for title.
   *
   * @return  String
   */
  public String getTitle() {
    return title;
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
   * setter method for exps count.
   *
   * @param  expsCount  int
   */
  public void setExpsCount(int expsCount) {
    this.expsCount = expsCount;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for images.
   *
   * @param  images  String[]
   */
  public void setImages(String[] images) {
    this.images = images;
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

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for title.
   *
   * @param  title  String
   */
  public void setTitle(String title) {
    this.title = title;
  }
} // end class TopicCommand
