package com.yatu.filter;


import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yatu.data.ErrorCode;
import com.yatu.data.ResponseInfo;

import com.yatu.domain.ApplicationConfig;

import com.yatu.service.ApplicationService;

import com.yatu.util.SignNatureUtil;


/**
 * Created by hjf_mac on 10/26/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/26/2016 17:09
 */
@Component public class WebFilter implements Filter {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  public static final Logger logger = LoggerFactory.getLogger(WebFilter.class);

  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  @Autowired ApplicationService applicationService;

  /** TODO: DOCUMENT ME! */
  @Autowired ObjectMapper objectMapper;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  javax.servlet.Filter#destroy()
   */
  @Override public void destroy() { }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
   *       javax.servlet.FilterChain)
   */
  @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    ServletRequest sr = null;

    System.out.println("content type is " + request.getContentType());

    if (request instanceof HttpServletRequest) {
      sr = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
    }

    String name = ((HttpServletRequest) request).getHeader("appName");
    String id   = ((HttpServletRequest) request).getHeader("appID");

    boolean valid = validateHeader(id, name);

    if (!valid) {
      logger.error("非法消息头.");
      outToJson(response);

      return;
    }

    String body = HttpHelper.getBodyString(sr);


    Map<String, Object> json = objectMapper.readValue(body, TreeMap.class);

    logger.info("the request params is " + json);

    if (StringUtils.isEmpty(json.get("sign"))) {
      logger.error("非法请求，没有签名");

      outToJson(response);

      return;
    }

    if (StringUtils.isEmpty(json.get("appKey"))) {
      logger.error("非法请求，没有appKey");

      outToJson(response);

      return;
    }

    String appkey = json.get("appKey").toString();

    valid = validateAppKey(appkey);

    if (!valid) {
      logger.error("非法请求，错误的appKey");

      outToJson(response);

      return;
    }

    String signNature = json.get("sign").toString();


    String backSign = SignNatureUtil.getSignNature(json, logger);

    logger.info("the back sign is [" + backSign + "]");

    if (!signNature.equals(backSign)) {
      logger.error("signNature failure.");
      outToJson(response);

      return;
    }

    if (sr == null) {
      chain.doFilter(request, response);
    } else {
      chain.doFilter(sr, response);
    }
  } // end method doFilter

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  javax.servlet.Filter#init(javax.servlet.FilterConfig)
   */
  @Override public void init(FilterConfig filterConfig) throws ServletException { }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * outToJson.
   *
   * @param   response  ServletResponse
   *
   * @throws  IOException  exception
   */
  public void outToJson(ServletResponse response) throws IOException {
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    httpResponse.setCharacterEncoding("UTF-8");
    httpResponse.setContentType("application/json; charset=utf-8");
    httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

    ResponseInfo<String> info = new ResponseInfo<>(ErrorCode.UNAUTHORIZED.CODE(), ErrorCode.UNAUTHORIZED.DESC(), "");
    httpResponse.getWriter().write(objectMapper.writeValueAsString(info));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * validateAppKey.
   *
   * @param   key  String
   *
   * @return  boolean
   */
  public boolean validateAppKey(String key) {
    ApplicationConfig applicationConfig = applicationService.findConfigInfoByConfigName("appKey");

    if (applicationConfig == null) {
      return true;
    }

    if (!applicationConfig.getConfigValue().equals(key)) {
      return false;
    }

    return true;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * validateHeader.
   *
   * @param   id    String
   * @param   name  String
   *
   * @return  boolean
   */
  public boolean validateHeader(String id, String name) {
    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(id)) {
      return false;
    }

    List<ApplicationConfig> list = applicationService.findConfigInfo("appID,appName");

    String appID   = "";
    String appName = "";

    for (ApplicationConfig ac : list) {
      if ("appID".equals(ac.getConfigName())) {
        appID = ac.getConfigValue();
      }

      if ("appName".equals(ac.getConfigName())) {
        appName = ac.getConfigValue();
      }
    }

    if (!name.equals(appName) || !id.equals(appID)) {
      return false;
    }

    return true;
  } // end method validateHeader
} // end class WebFilter
