package com.yatu.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

import javax.servlet.ServletRequest;


/**
 * Created by hjf_mac on 10/26/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/26/2016 17:07
 */
public class HttpHelper {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for body string.
   *
   * @param   request  ServletRequest
   *
   * @return  String
   */
  public static String getBodyString(ServletRequest request) {
    StringBuilder  sb          = new StringBuilder();
    InputStream    inputStream = null;
    BufferedReader reader      = null;

    try {
      inputStream = request.getInputStream();
      reader      = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

      String line = "";

      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } // end try-catch-finally

    return sb.toString();
  } // end method getBodyString

} // end class HttpHelper
