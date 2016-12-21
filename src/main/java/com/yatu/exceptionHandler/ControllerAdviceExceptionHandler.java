package com.yatu.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import com.yatu.data.ErrorCode;
import com.yatu.data.ResponseInfo;
import com.yatu.exception.InvalidParamsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 13:13
 */
@ControllerAdvice public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleHttpMessageNotReadable(org.springframework.http.converter.HttpMessageNotReadableException,
   *       org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
   *       org.springframework.web.context.request.WebRequest)
   */
  @Override protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {
    ResponseInfo<String> info = new ResponseInfo<>(ErrorCode.BAD_REQUEST.CODE(), ex.getCause().getMessage(), "");

    return new ResponseEntity<Object>(info, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * handleInvalidParamsException.
   *
   * @param   ex       InvalidParamsException
   * @param   request  HttpServletRequest
   *
   * @return  ResponseEntity
   */
  @ExceptionHandler({ InvalidParamsException.class })
  protected ResponseEntity<ResponseInfo> handleInvalidParamsException(final InvalidParamsException ex,
    final HttpServletRequest request) {
    ResponseInfo<String> info = new ResponseInfo<>(ex.getErrorCode().CODE(), ex.getMessage(), "");

    return new ResponseEntity<ResponseInfo>(info, new HttpHeaders(), ex.getHttpStatus());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException,
   *       org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
   *       org.springframework.web.context.request.WebRequest)
   */
  @Override protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {
    String               msg  = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    ResponseInfo<String> info = new ResponseInfo<>(ErrorCode.BAD_REQUEST.CODE(), msg, "");

    return new ResponseEntity<Object>(info, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }
} // end class ControllerAdviceExceptionHandler
