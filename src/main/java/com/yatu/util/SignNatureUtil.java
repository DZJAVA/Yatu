package com.yatu.util;

import java.nio.charset.Charset;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;


/**
 * Created by hjf_mac on 10/27/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/27/2016 10:53
 */
public class SignNatureUtil {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for sign nature.
   *
   * @param   map     Map
   * @param   logger  Logger
   *
   * @return  String
   */
  public static String getSignNature(Map<String, Object> map, Logger logger) {
    Set<String> keySet = map.keySet();

    StringBuilder sb    = new StringBuilder();
    Object        value = null;

    for (String key : keySet) {
      value = map.get(key);

      if (value == null) {
        continue;
      }

      if (key.equals("sign")) {
        continue;
      }

      if (value instanceof List) {
        String strs = StringUtils.collectionToCommaDelimitedString((List) value);
        strs = StringUtils.replace(strs, ",", "");

        if (StringUtils.hasText(strs)) {
          sb.append(key).append("=").append(strs).append("&");
        }

        continue;
      }

      if (StringUtils.hasText(value.toString())) {
        sb.append(key).append("=").append(value.toString()).append("&");
      }
    } // end for

    if (StringUtils.isEmpty(sb.toString())) {
      return "";
    }

    String result = sb.toString().substring(0, sb.length() - 1);

    if (map.get("appKey") != null) {
      result = result + map.get("appKey").toString();
    }

    if (logger != null) {
      logger.info("the order params is [" + result + "]");
    }

    return DigestUtils.md5DigestAsHex(result.getBytes(Charset.forName("UTF-8"))).toUpperCase();
  } // end method getSignNature

} // end class SignNatureUtil
