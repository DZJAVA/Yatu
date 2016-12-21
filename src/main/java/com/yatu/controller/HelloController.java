package com.yatu.controller;

// import com.mangofactory.swagger.annotations.ApiIgnore;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:rojer@ozstrategy.com">Rojer Luo</a>
 * @version  11/05/2014 11:29
 */
@ApiIgnore @Controller public class HelloController {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * index.
   *
   * @return  String
   */
  @RequestMapping(
    value  = "/",
    method = RequestMethod.GET
  )
  @ResponseBody public Map index() {
    Map<String, Object> map = new HashMap<>();
    map.put("state", 200);
    map.put("msg", "success");
    map.put("data", "hello");
    return map; // ("redirect:/docs/index.html");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * ping.
   *
   * @return  String
   */
  @RequestMapping(
    value  = "/ping/{id}",
    method = RequestMethod.GET
  )
  @ResponseBody public String ping() {
    return "Ping!";
  }
} // end class HelloController
