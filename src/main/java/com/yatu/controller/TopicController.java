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

import com.yatu.command.BaseWorksRespCommand;
import com.yatu.command.TopicCommand;

import com.yatu.data.CommitWorksParams;
import com.yatu.data.ErrorCode;
import com.yatu.data.PageParams;
import com.yatu.data.ResponseInfo;
import com.yatu.data.TopicIdParams;
import com.yatu.data.TopicParams;

import com.yatu.domain.Topic;

import com.yatu.exception.InvalidParamsException;

import com.yatu.service.TopicService;

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
  value       = "Topic Operation",
  position    = 3,
  description = "API for Topic"
)
@RequestMapping("/topic")
@RestController public class TopicController {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  /** TODO: DOCUMENT ME! */
  public static final Logger logger = LoggerFactory.getLogger(TopicController.class);

  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private TopicService topicService;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * commitDesign.
   *
   * @param   submitDesignParams  CommitWorksParams
   * @param   response            HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 1,
    value    = "Submit a design to a topic",
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
        message = "resource not found"
      ),
      @ApiResponse(
        code = 409,
        message = "The design already existed in the specify topic."
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/submitDesign"
  )
  @ResponseBody @ValidateToken public ResponseInfo commitDesign(
    @ApiParam(
      name     = "submitDesignParams",
      value    = "Submit design to topic info.",
      required = true
    )
    @RequestBody @Valid CommitWorksParams submitDesignParams, HttpServletResponse response)
    throws InvalidParamsException {
    topicService.commitWorks(submitDesignParams);

    return new ResponseInfo<>(ErrorCode.SUCCESS.CODE(), "", "");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * createTopic.
   *
   * @param   topicParams  TopicParams
   * @param   response     HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 2,
    value    = "Create a topic",
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
    value  = "/createTopic"
  )
  @ResponseBody @ValidateToken public ResponseInfo createTopic(
    @ApiParam(
      name     = "topicParams",
      value    = "The create topic info.",
      required = true
    )
    @RequestBody @Valid TopicParams topicParams, HttpServletResponse response) throws InvalidParamsException {
    Topic topic = topicService.createTopic(topicParams.populate());


    return new ResponseInfo<TopicCommand>(ErrorCode.SUCCESS.CODE(), "", new TopicCommand(topic));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for topic info by id.
   *
   * @param   topicIdParams  TopicIdParams
   * @param   response       HttpServletResponse
   *
   * @return  ResponseInfo
   *
   * @throws  InvalidParamsException  exception
   */
  @ApiOperation(
    position = 3,
    value    = "Get Topic Info",
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
        message = "Topic not found with the tid"
      ),
      @ApiResponse(
        code = 500,
        message = "Internal error"
      )
    }
  )
  @RequestMapping(
    method = RequestMethod.POST,
    value  = "/getTopicDetailById"
  )
  @ResponseBody public ResponseInfo getTopicInfoById(
    @ApiParam(
      name     = "topicIdParams",
      value    = "The topic's desgins page info.",
      required = true
    )
    @RequestBody @Valid TopicIdParams topicIdParams, HttpServletResponse response) throws InvalidParamsException {
    topicIdParams.initPage();

    List<Topic> list = topicService.findTopicById(topicIdParams);

    List<BaseWorksRespCommand> resp = new ArrayList<>();
    Map                        map  = new HashMap<>();
    map.put("topic", new TopicCommand(list.get(0)));

    for (Topic t : list) {
      if (t.getWorks() != null) {
        resp.add(new BaseWorksRespCommand(t.getWorks()));
      }
    }

    map.put("exps", resp);

    return new ResponseInfo<Map>(ErrorCode.SUCCESS.CODE(), "", map);

  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * .
   *
   * @param   pageParams  criteria ElCriteria
   * @param   response    HttpServletResponse
   *
   * @return  ApiResponse
   *
   * @throws  com.yatu.exception.InvalidParamsException  exception
   */
  @ApiOperation(
    position = 4,
    value    = "Get Topics By Page",
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
    value  = "/getTopics"
  )
  @ResponseBody public ResponseInfo getTopicListByPage(
    @ApiParam(
      name     = "pageParams",
      value    = "The page info by page.",
      required = true
    )
    @RequestBody @Valid PageParams pageParams, HttpServletResponse response) throws InvalidParamsException {
    pageParams.initPage();

    List<Topic>        list = topicService.findTopicsByPage(pageParams);
    List<TopicCommand> resp = new ArrayList<>();

    if (list != null) {
      for (Topic t : list) {
        resp.add(new TopicCommand(t));
      }
    }

    return new ResponseInfo<List>(ErrorCode.SUCCESS.CODE(), "", resp);
  }

} // end class TopicController
