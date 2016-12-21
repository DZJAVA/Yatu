package com.yatu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;

import springfox.documentation.schema.ModelRef;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created by Yang Wang on 6/12/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/12/2016 15:52
 */
@Profile(value = {"dev", "prod"})
@ComponentScan(basePackages = { "com.ozstrategy.strategy.el.controller" })
@Configuration @EnableSwagger2
public class SwaggerConfig {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @Bean public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
// .groupName("test")
      .genericModelSubstitutes(DeferredResult.class)
// //.genericModelSubstitutes(ResponseEntity.class)
      .useDefaultResponseMessages(false).forCodeGeneration(true)
////        .pathMapping("/api")//api测试请求地址
      .select().paths(paths()) // 过滤的接口
      .build().apiInfo(apiInfo());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Yatu Service API") // title

// .description("...")                                  // desc
// .version("1.0")                                      // version
// .termsOfServiceUrl("Terms of Service")               // terms of service
// .contact(new Contact("OZ Intel", "OZ Intel", "ozbuilder@ozstrategy.com")).license("Licensed to OZ Inc")
// .licenseUrl("http://www.ozintel.cn")                 // license url
      .build();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  private Predicate<String> paths() {
    return Predicates.not(PathSelectors.regex("/error"));
  }
} // end class SwaggerConfig
