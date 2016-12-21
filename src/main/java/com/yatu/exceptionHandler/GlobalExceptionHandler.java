package com.yatu.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;


/**
 * Created by hjf_mac on 9/22/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/22/2016 13:13
 */
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * handleMethodArgumentNotValidException.
   *
   * @param   request    HttpServletRequest
   * @param   exception  ex MethodArgumentNotValidException
   *
   * @return  ResponseInfo
   */
//  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Object MethodArgumentNotValidHandler(HttpServletRequest request,
    MethodArgumentNotValidException exception) throws Exception {
    System.out.println(exception);

    return null;
  }
}
