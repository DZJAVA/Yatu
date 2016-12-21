package com.yatu.test.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.context.annotation.Bean;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * Created by hjf_mac on 9/30/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/30/2016 17:25
 */
@TestConfiguration public class Config {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * mappingJackson2HttpMessageConverter.
   *
   * @return  MappingJackson2HttpMessageConverter
   */
  @Bean public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    ObjectMapper                        objectMapper  = new ObjectMapper();

    jsonConverter.setObjectMapper(objectMapper);

    return jsonConverter;
  }
}
