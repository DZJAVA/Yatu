package com.yatu.test;


import org.junit.runner.RunWith;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by Yang Wang on 6/14/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/14/2016 12:13
 */
@ActiveProfiles("test")
@ComponentScan(basePackages = { "com.yatu.repository" })
@DataJpaTest
@RunWith(SpringRunner.class)
public abstract class BaseRepositoryTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  public ObjectMapper objectMapper = new ObjectMapper();

}
