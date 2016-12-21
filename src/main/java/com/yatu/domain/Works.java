package com.yatu.domain;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by hjf_mac on 9/20/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/20/2016 15:44
 */
public class Works extends BaseDomain {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String contentUrl;
  private int    height;
  private String id;
  private int    liked;
  private User   likedUser;
  private String thumbnail;

  private Set<Topic> topics = new HashSet<>();
  private int        type;
  private User       user;
  private int        width;

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
   * getter method for height.
   *
   * @return  int
   */
  public int getHeight() {
    return height;
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
   * getter method for liked.
   *
   * @return  int
   */
  public int getLiked() {
    return liked;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for liked user.
   *
   * @return  User
   */
  public User getLikedUser() {
    return likedUser;
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
   * getter method for topics.
   *
   * @return  Set
   */
  public Set<Topic> getTopics() {
    return topics;
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
   * getter method for user.
   *
   * @return  User
   */
  public User getUser() {
    return user;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for width.
   *
   * @return  int
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
   * setter method for height.
   *
   * @param  height  int
   */
  public void setHeight(int height) {
    this.height = height;
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
   * setter method for liked.
   *
   * @param  liked  int
   */
  public void setLiked(int liked) {
    this.liked = liked;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for liked user.
   *
   * @param  likedUser  User
   */
  public void setLikedUser(User likedUser) {
    this.likedUser = likedUser;
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
   * setter method for topics.
   *
   * @param  topics  Set
   */
  public void setTopics(Set<Topic> topics) {
    this.topics = topics;
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
   * setter method for user.
   *
   * @param  user  User
   */
  public void setUser(User user) {
    this.user = user;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for width.
   *
   * @param  width  int
   */
  public void setWidth(int width) {
    this.width = width;
  }
} // end class Works
