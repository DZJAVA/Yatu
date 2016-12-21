package com.yatu.exception;

import org.springframework.http.HttpStatus;

import com.yatu.data.ErrorCode;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 13:18
 */
public class InvalidParamsException extends Exception {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  protected ErrorCode errorCode = ErrorCode.INVALID_PAGE;

  /** TODO: DOCUMENT ME! */
  protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // default value;

  //~ Constructors -----------------------------------------------------------------------------------------------------


  /**
   * Creates a new InvalidParamsException object.
   *
   * @param  message  String
   * @param  code     ErrorCode
   * @param  status   HttpStatus
   */
  public InvalidParamsException(String message, ErrorCode code, HttpStatus status) {
    super(message);
    this.errorCode  = code;
    this.httpStatus = status;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for error code.
   *
   * @return  ErrorCode
   */
  public ErrorCode getErrorCode() {
    return errorCode;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for http status.
   *
   * @return  HttpStatus
   */
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for error code.
   *
   * @param  errorCode  ErrorCode
   */
  public void setErrorCode(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for http status.
   *
   * @param  httpStatus  HttpStatus
   */
  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }
} // end class InvalidParamsException
