package com.yatu.config;

import com.yatu.filter.WebFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by hjf_mac on 10/26/16.
 */
@Configuration
public class FilterConfig {

  @Bean
  public Filter webFilter() {
    return new WebFilter();
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(webFilter());
    filterRegistrationBean.addUrlPatterns("/user/*", "/topic/*", "/design/*");
    filterRegistrationBean.setOrder(1);
    return  filterRegistrationBean;
  }

}
