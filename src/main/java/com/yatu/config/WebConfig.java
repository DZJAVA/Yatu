package com.yatu.config;


import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.yatu.interceptor.GlobalInterceptor;


/**
 * Created by Yang Wang on 6/12/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/12/2016 14:11
 */
@Configuration @EnableWebMvc public class WebConfig extends WebMvcConfigurerAdapter {
  //~ Methods ----------------------------------------------------------------------------------------------------------

//  @Bean
//  GlobalInterceptor globalInterceptor() {
//    return new GlobalInterceptor();
//  }

//  @Override public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(globalInterceptor()).addPathPatterns("/user/*", "/topic/*", "/design/*");
//    super.addInterceptors(registry);
//  }

  //~ ------------------------------------------------------------------------------------------------------------------


  /**
   * @see  org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
   */
  @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("*.html").addResourceLocations("/api-docs").setCachePeriod(31556926);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
   */
  @Override public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureMessageConverters(java.util.List)
   */
  @Override public void configureMessageConverters(final List<HttpMessageConverter<?>> messageConverters) {
    messageConverters.add(createJackson2Converter());
    StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
    stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
    messageConverters.add(stringHttpMessageConverter);

    super.configureMessageConverters(messageConverters);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  private MappingJackson2HttpMessageConverter createJackson2Converter() {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(objectMapper());

    return converter;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  private ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    return objectMapper;
  }
} // end class WebConfig
