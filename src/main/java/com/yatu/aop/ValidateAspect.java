package com.yatu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Component;

import com.yatu.data.BaseParams;
import com.yatu.data.ErrorCode;

import com.yatu.exception.InvalidParamsException;

import com.yatu.service.UserService;


/**
 * Created by hjf_mac on 10/26/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/26/2016 15:29
 */
@Aspect @Component public class ValidateAspect {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Autowired UserService userService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * doBefore.
   *
   * @param   joinPoint     JoinPoint
   * @param   validateToken  ValidateSign
   *
   * @throws  InvalidParamsException  exception
   */
  @Before("@annotation(validateToken)")
  public void doBefore(JoinPoint joinPoint, final ValidateToken validateToken) throws InvalidParamsException {
    System.out.println("into aspect.");

    Object[] objects = joinPoint.getArgs();

    BaseParams params = null;

    for (Object o : objects) {
      if (o instanceof BaseParams) {
        params = (BaseParams)o;

        break;
      }
    }

    validateToken(params);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * validateToken.
   *
   * @param   baseParams  BaseParams
   *
   * @throws  InvalidParamsException  exception
   */
  public void validateToken(BaseParams baseParams) throws InvalidParamsException {
      Integer result = userService.validateUserToken(baseParams.getUserid(), baseParams.getToken());

      if (result.intValue() == 0) {
        throw new InvalidParamsException(ErrorCode.UNAUTHORIZED.DESC(), ErrorCode.UNAUTHORIZED,
          HttpStatus.UNAUTHORIZED);
      }
  }
} // end class ValidateAspect
