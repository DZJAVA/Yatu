package com.yatu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yatu.aop.ValidateToken;

import com.yatu.command.BaseUserCommand;
import com.yatu.command.SingleWorksRespCommand;
import com.yatu.command.UserCommand;
import com.yatu.command.UserWorksCommand;

import com.yatu.data.ErrorCode;
import com.yatu.data.FollowUserIdParams;
import com.yatu.data.FollowUserParams;
import com.yatu.data.ResponseInfo;
import com.yatu.data.UserIdParams;
import com.yatu.data.UserPageParams;
import com.yatu.data.UserParams;

import com.yatu.domain.User;
import com.yatu.domain.Works;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.UserRepository;

import com.yatu.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/26/2016 15:24
 */
@Api(
  value       = "User Operation",
  position    = 2,
  description = "API for User"
)
@RequestMapping("/user")
@RestController public class UserController {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  public static final Logger logger = LoggerFactory.getLogger(UserController.class);

  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private UserService userService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * createUser.
   *
   * @param   userParams  UserParams
   * @param   response    HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 1,
    value    = "Third party login",
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
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/thirdPartyLogin"
  )
  @ResponseBody public ResponseInfo createUser(
    @ApiParam(
      name     = "userParams",
      value    = "The login user info.",
      required = true
    )
    @RequestBody @Valid UserParams userParams, HttpServletResponse response) throws InvalidParamsException {
    User user = userService.createOrUpdateUser(userParams.populate());

    Map map = new HashMap<>();
    map.put("userId", user.getId());
    map.put("token", user.getPassword());

    return new ResponseInfo<>(ErrorCode.SUCCESS.CODE(), "", map);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserById.
   *
   * @param   userIdParams  UserIdParams
   * @param   response      HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 2,
    value    = "Get a User By ID",
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
        code = 404,
        message = "User not found with the id."
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/getUserDetailById"
  )
  @ResponseBody public ResponseInfo findUserById(
    @ApiParam(
      name     = "userIdParams",
      value    = "The user id info.",
      required = true
    )
    @RequestBody @Valid UserIdParams userIdParams, HttpServletResponse response) throws InvalidParamsException {
    List<User> result = userService.findUserById(userIdParams.getUid());

    UserCommand userCommand = new UserCommand(result);

    return new ResponseInfo<UserCommand>(ErrorCode.SUCCESS.CODE(), "", userCommand);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserWorks.
   *
   * @param   userPageParams  UserPageParams
   * @param   response        HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 3,
    value    = "Get User's fans By Page",
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
        code = 404,
        message = "User not found with the id."
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/getUserFans"
  )
  @ResponseBody public ResponseInfo findUserFans(
    @ApiParam(
      name     = "userPageParams",
      value    = "The user's fans page info.",
      required = true
    )
    @RequestBody @Valid UserPageParams userPageParams, HttpServletResponse response) throws InvalidParamsException {
    userPageParams.initPage();

    List<User> result = userService.findUserFans(userPageParams);

    if ((result != null) && (result.size() > 0)) {
      List<BaseUserCommand> resp = new ArrayList<>();

      for (User user : result) {
        resp.add(new BaseUserCommand(user));
      }

      return new ResponseInfo<List>(ErrorCode.SUCCESS.CODE(), "", resp);
    } else {
      return new ResponseInfo<>(ErrorCode.SUCCESS.CODE(), "", null);
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserFollowedUsers.
   *
   * @param   userPageParams  UserPageParams
   * @param   response        HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 4,
    value    = "Get Followed Users By Page",
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
        code = 404,
        message = "User not found with the id."
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/getFollowedUsers"
  )
  @ResponseBody public ResponseInfo findUserFollowedUsers(
    @ApiParam(
      name     = "userPageParams",
      value    = "The user followed users's page info.",
      required = true
    )
    @RequestBody @Valid UserPageParams userPageParams, HttpServletResponse response) throws InvalidParamsException {
    userPageParams.initPage();
    ;

    List<User> result = userService.findUserFollows(userPageParams);

    if ((result != null) && (result.size() > 0)) {
      List<BaseUserCommand> resp = new ArrayList<>();

      for (User user : result) {
        resp.add(new BaseUserCommand(user));
      }

      return new ResponseInfo<List>(ErrorCode.SUCCESS.CODE(), "", resp);
    } else {
      return new ResponseInfo<>(ErrorCode.SUCCESS.CODE(), "", null);
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findUserWorks.
   *
   * @param   userPageParams  UserPageParams
   * @param   response        HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 5,
    value    = "Get User's Designs By Page",
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
        code = 404,
        message = "User not found with the id."
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/getUserDesigns"
  )
  @ResponseBody public ResponseInfo findUserWorks(
    @ApiParam(
      name     = "userPageParams",
      value    = "The user's designs page info.",
      required = true
    )
    @RequestBody @Valid UserPageParams userPageParams, HttpServletResponse response) throws InvalidParamsException {
    userPageParams.initPage();

    List<Works> result = userService.findUserWorksByPage(userPageParams);

    if ((result != null) && (result.size() > 0)) {
      List<UserWorksCommand> resp = new ArrayList<>();

      for (Works works : result) {
        resp.add(new UserWorksCommand(works));
      }

      return new ResponseInfo<List>(ErrorCode.SUCCESS.CODE(), "", resp);
    } else {
      return new ResponseInfo<>(ErrorCode.SUCCESS.CODE(), "", null);
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * likeWorks.
   *
   * @param   followUserParams  likeWorksParams WorksIdParams
   * @param   response          HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  com.yatu.exception.InvalidParamsException  exception
   */
  @ApiOperation(
    position = 6,
    value    = "Follow a User",
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
        code = 404,
        message = "User not found with the id."
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/followUser"
  )
  @ResponseBody @ValidateToken public ResponseInfo followUser(
    @ApiParam(
      name     = "followUserParams",
      value    = "The follow user params.",
      required = true
    )
    @RequestBody @Valid FollowUserParams followUserParams, HttpServletResponse response) throws InvalidParamsException {
    userService.followUser(followUserParams.getUserid(), followUserParams.getFollowUId(),
      followUserParams.getFollowed() ? 1 : 0);

    return new ResponseInfo<SingleWorksRespCommand>(ErrorCode.SUCCESS.CODE(), "", null);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for liked works.
   *
   * @param   followUserIdParams  WorksIdParams
   * @param   response            HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  com.yatu.exception.InvalidParamsException  exception
   */
  @ApiOperation(
    position = 7,
    value    = "Follow a User Or Not",
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
        code = 404,
        message = "User not found with the id."
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/isFollowedUser"
  )
  @ResponseBody @ValidateToken public ResponseInfo isFollowedUser(
    @ApiParam(
      name     = "userIdParams",
      value    = "The design info.",
      required = true
    )
    @RequestBody @Valid FollowUserIdParams followUserIdParams, HttpServletResponse response)
    throws InvalidParamsException {
    boolean result = userService.isFollowedUser(followUserIdParams.getUserid(), followUserIdParams.getFollowUId());
    Map     map    = new HashMap<>();
    map.put("followed", result ? 1 : 0);

    return new ResponseInfo<Map>(ErrorCode.SUCCESS.CODE(), "", map);
  }

} // end class UserController
