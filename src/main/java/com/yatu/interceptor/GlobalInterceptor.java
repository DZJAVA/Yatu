package com.yatu.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yatu.data.ErrorCode;

import com.yatu.domain.ApplicationConfig;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.ApplicationConfigRepository;

import com.yatu.service.ApplicationService;


/**
 * Created by hjf_mac on 9/27/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/27/2016 20:25
 */
//@Component
public class GlobalInterceptor implements HandlerInterceptor {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  private static String appName;
  private static String appID;

  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Autowired ApplicationService applicationService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,
   *       java.lang.Object, java.lang.Exception)
   */
  @Override public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
    Object o, Exception e) throws Exception { }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,
   *       java.lang.Object, org.springframework.web.servlet.ModelAndView)
   */
  @Override public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
    Object o, ModelAndView modelAndView) throws Exception { }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,
   *       java.lang.Object)
   */
  @Override public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
    Object o) throws Exception {

    System.out.println("into intercptor.");

    String name = httpServletRequest.getHeader("appName");
    String id   = httpServletRequest.getHeader("appID");
    initAppInfo(httpServletRequest);

    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(id)) {
      throw new InvalidParamsException(ErrorCode.UNAUTHORIZED.DESC(), ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    if (!name.equals(appName) || !id.equals(appID)) {
      throw new InvalidParamsException(ErrorCode.UNAUTHORIZED.DESC(), ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    return true;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  private void initAppInfo(HttpServletRequest request) {

    if (StringUtils.isEmpty(appID)) {
      List<ApplicationConfig> list = applicationService.findConfigInfo("appID,appName");

      for (ApplicationConfig a : list) {
        if ("appID".equals(a.getConfigName())) {
          appID = a.getConfigValue();
        }

        if ("appName".equals(a.getConfigName())) {
          appName = a.getConfigValue();
        }
      }
    }
  }
} // end class GlobalInterceptor
