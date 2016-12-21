package com.yatu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * Created by Yang Wang on 6/12/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/12/2016 14:13
 */
@SpringBootApplication public class Application extends SpringBootServletInitializer {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * main.
   *
   * @param   args  String[]
   *
   * @throws  Exception  exception
   */
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   *
   * configure.
   *
   * @param   application  SpringApplicationBuilder
   *
   * @return  SpringApplicationBuilder
   */
  @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

} // end class Application
