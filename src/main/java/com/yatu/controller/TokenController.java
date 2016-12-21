package com.yatu.controller;

import com.qiniu.util.Auth;
import com.yatu.data.ErrorCode;
import com.yatu.data.ResponseInfo;
import com.yatu.exception.InvalidParamsException;
import com.yatu.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/27/2016 09:46
 */
@Api(
  value       = "Token Operation",
  position    = 4,
  description = "API for Token"
)
@RequestMapping("/token")
@RestController public class TokenController {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  public static final Logger logger = LoggerFactory.getLogger(TokenController.class);

  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private UserRepository userRepository;

  @ApiOperation(
    position = 1,
    value    = "Get a Upload Token",
    consumes = "application/json",
    produces = "application/json"
  )
  @ApiResponses(
    value = {
      @ApiResponse(
        code = 200,
        message = "success"
      ),
      @ApiResponse(
        code = 400,
        message = "Bad Request"
      ),
      @ApiResponse(
        code = 401,
        message = "Unauthorized access to use this resource"
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @PostMapping(value = "/getUploadToken")
  @ResponseBody public ResponseInfo getUploadToken()
    throws InvalidParamsException {
    String access_key = "ieIj6VDvSOmFScDFiPhGMgGkXqlALCGg6tINgyCQ";
    String secret_key = "pCo83VbVP40K3afJZ5MZWUeQtIIz7EiVlh4oGwfw";
    String buket_name = "yatu1";

    Auth auth = Auth.create(access_key, secret_key);
    String token = auth.uploadToken(buket_name);

    return new ResponseInfo<>(ErrorCode.SUCCESS.CODE(), "", token);
  }

} // end class TopicController
