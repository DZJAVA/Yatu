package com.yatu.data;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Created by Yang Wang on 6/13/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/13/2016 17:04
 */
public class ResponseInfo<T> {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  protected T data;

  /** TODO: DOCUMENT ME! */
  @ApiModelProperty(
    value    = "Error Message",
    position = 2,
    required = true,
    example  = ""
  )
  protected String msg;

  /** TODO: DOCUMENT ME! */
  @ApiModelProperty(
    value    = "state code",
    required = true,
    position = 1,
    example  = "200"
  )
  protected int state = 200;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new ResponseInfo object.
   */
  public ResponseInfo() { }

  /**
   * Creates a new ResponseInfo object.
   *
   * @param  errorCode     int
   * @param  errorMessage  String
   * @param  object        T
   */
  public ResponseInfo(int errorCode, String errorMessage, T object) {
    this.state = errorCode;
    this.msg   = errorMessage;
    this.data  = object;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for data.
   *
   * @return  T
   */
  public T getData() {
    return data;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for msg.
   *
   * @return  String
   */
  public String getMsg() {
    return msg;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for state.
   *
   * @return  int
   */
  public int getState() {
    return state;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for data.
   *
   * @param  data  T
   */
  public void setData(T data) {
    this.data = data;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for msg.
   *
   * @param  msg  String
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for state.
   *
   * @param  state  int
   */
  public void setState(int state) {
    this.state = state;
  }

} // end class ResponseInfo
