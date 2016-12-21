package com.yatu.data;

/**
 * Created by Yang Wang on 6/13/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/13/2016 17:08
 */
public enum ErrorCode {
  //~ Enum constants ---------------------------------------------------------------------------------------------------

  SUCCESS(200, "SUCCESS"),                       // SUCCESS
  BAD_REQUEST(400, "Bad Request"),
  UNAUTHORIZED(401, "Unauthorized access to use this resource"),
  NOT_FOUND(404, "Resource not found"), // Invalid Expression
  CONFLICT(409, "Already existed"),
  INTERNAL_ERROR(500, "Internal Error"),          // Internal Error
  INVALID_PAGE(1001, "Invalid page params."),
  TOPIC_NOT_FOUND(1002, "Topic not found with the id."),
  WORKS_NOT_FOUND(1003, "Design not found with the id."),
  USER_NOT_FOUND(1004, "User not found with the id."),
  NO_RIGHT_ACESS(1005, "No right access the resource."),
  ALREADY_EXISTED(1006, "The design already existed in the specify topic.")
  ;

  //~ Instance fields --------------------------------------------------------------------------------------------------

  private int    code;
  private String desc;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new ErrorCode object.
   *
   * @param  code  int
   * @param  desc  String
   */
  ErrorCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * CODE.
   *
   * @return  int
   */
  public int CODE() {
    return code;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DESC.
   *
   * @return  String
   */
  public String DESC() {
    return desc;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for code.
   *
   * @param  code  int
   */
  public void setCode(int code) {
    this.code = code;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for desc.
   *
   * @param  desc  String
   */
  public void setDesc(String desc) {
    this.desc = desc;
  }
}
