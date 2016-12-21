package com.yatu.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;


/**
 * Created by hjf_mac on 10/26/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  10/26/2016 11:31
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private final byte[] body;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Constructs a request object wrapping the given request.
   *
   * @param   request  The request to wrap
   *
   * @throws  IllegalArgumentException  if the request is null
   * @throws  IOException               exception
   */
  public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    body = StreamUtils.copyToByteArray(request.getInputStream());
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  javax.servlet.ServletRequestWrapper#getInputStream()
   */
  @Override public ServletInputStream getInputStream() throws IOException {
    final ByteArrayInputStream bais = new ByteArrayInputStream(body);


    return new ServletInputStream() {
      @Override public boolean isFinished() {
        return false;
      }

      @Override public boolean isReady() {
        return false;
      }

      @Override public void setReadListener(ReadListener listener) { }

      @Override public int read() throws IOException {
        return bais.read();
      }
    };
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  javax.servlet.ServletRequestWrapper#getReader()
   */
  @Override public BufferedReader getReader() throws IOException {
// return super.getReader();
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }
} // end class BodyReaderHttpServletRequestWrapper
