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

import org.springframework.util.StreamUtils;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yatu.aop.ValidateToken;

import com.yatu.command.BaseWorksRespCommand;
import com.yatu.command.SingleWorksRespCommand;

import com.yatu.data.ErrorCode;
import com.yatu.data.LikeWorksParams;
import com.yatu.data.PageParams;
import com.yatu.data.ResponseInfo;
import com.yatu.data.WorksIdParams;
import com.yatu.data.WorksParams;

import com.yatu.domain.Works;

import com.yatu.exception.InvalidParamsException;

import com.yatu.repository.UserRepository;

import com.yatu.service.UserService;
import com.yatu.service.WorksService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * TODO: DOCUMENT ME!
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/27/2016 09:46
 */
@Api(
  value       = "Designs Operation",
  position    = 1,
  description = "API for Design"
)
@RequestMapping("/design")
@RestController public class WorksController {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  public static final Logger logger = LoggerFactory.getLogger(WorksController.class);

  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private WorksService worksService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * createTopic.
   *
   * @param   designParams  WorksParams
   * @param   response      HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 1,
    value    = "Create a design",
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
    value  = "/createDesign"
  )
  @ResponseBody @ValidateToken public ResponseInfo createWorks(
    @ApiParam(
      name     = "designParams",
      value    = "The create design info.",
      required = true
    )
    @RequestBody @Valid WorksParams designParams, HttpServletResponse response) throws InvalidParamsException {
    Works works = worksService.createWorks(designParams.populate());

    Map map = new HashMap<>();
    map.put("eid", works.getId());

    return new ResponseInfo<>(ErrorCode.SUCCESS.CODE(), "", map);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * findDesignById.
   *
   * @param   designIdParams  WorksIdParams
   * @param   response        HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 2,
    value    = "Get Design Detail By ID",
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
        message = "Design not found with the id."
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/getDesignDetailById"
  )
  @ResponseBody public ResponseInfo findDesignById(
    @ApiParam(
      name     = "designIdParams",
      value    = "The design id info.",
      required = true
    )
    @RequestBody @Valid WorksIdParams designIdParams, HttpServletResponse response) throws InvalidParamsException {
    List<Works> list = worksService.findWorksById(designIdParams.getEid());

    SingleWorksRespCommand command = new SingleWorksRespCommand(list, designIdParams.getUserid());

    return new ResponseInfo<SingleWorksRespCommand>(ErrorCode.SUCCESS.CODE(), "", command);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * verify.
   *
   * @param   pageParams  criteria ElCriteria
   * @param   response    HttpServletResponse
   *
   * @return  ApiResponse
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 3,
    value    = "Get Public Designs By Page",
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
    value  = "/getPublicDesigns"
  )
  @ResponseBody public ResponseInfo getPublicDesigns(
    @ApiParam(
      name     = "pageParams",
      value    = "The design page info.",
      required = true
    )
    @RequestBody @Valid PageParams pageParams, HttpServletResponse response) throws InvalidParamsException {
    pageParams.initPage();

    List<Works>                list = worksService.findPublicWorks(pageParams);
    List<BaseWorksRespCommand> resp = new ArrayList<>();

    if (list != null) {
      for (Works w : list) {
        resp.add(new BaseWorksRespCommand(w));
      }
    }

    return new ResponseInfo<List>(ErrorCode.SUCCESS.CODE(), "", resp);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for liked works.
   *
   * @param   designIdParams  WorksIdParams
   * @param   response        HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 4,
    value    = "Like a Design Or Not",
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
        message = "Design not found with the id."
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/isLikedDesign"
  )
  @ResponseBody @ValidateToken public ResponseInfo isLikedWorks(
    @ApiParam(
      name     = "designIdParams",
      value    = "The design id info.",
      required = true
    )
    @RequestBody @Valid WorksIdParams designIdParams, HttpServletResponse response) throws InvalidParamsException {
    boolean result = worksService.isLikedWorks(designIdParams.getUserid(), designIdParams.getEid());
    Map     map    = new HashMap<>();
    map.put("liked", result ? 1 : 0);

    return new ResponseInfo<Map>(ErrorCode.SUCCESS.CODE(), "", map);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * likeWorks.
   *
   * @param   likeWorksParams  WorksIdParams
   * @param   response         HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 5,
    value    = "Like a Design",
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
    value  = "/likeDesign"
  )
  @ResponseBody @ValidateToken public ResponseInfo likeWorks(
    @ApiParam(
      name     = "likeWorksParams",
      value    = "The params info.",
      required = true
    )
    @RequestBody @Valid LikeWorksParams likeWorksParams, HttpServletResponse response) throws InvalidParamsException {
    worksService.likeWorks(likeWorksParams.getUserid(), likeWorksParams.getEid(), likeWorksParams.getLiked() ? 1 : 0);

    return new ResponseInfo<SingleWorksRespCommand>(ErrorCode.SUCCESS.CODE(), "", null);
  }

} // end class WorksController
