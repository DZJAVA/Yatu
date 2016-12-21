package com.yatu.data;

import com.yatu.exception.InvalidParamsException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * Created by hjf_mac on 9/21/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/21/2016 10:52
 */
@ApiModel
public class PageParams extends BaseParams {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @ApiModelProperty(
      value    = "The page count",
      required = true,
      example  = "10"
  )
  @NotNull(message = "count is required")
  private Integer count;

  @ApiModelProperty(
      value    = "The page",
      required = true,
      example  = "1"
  )
  @NotNull(message = "page is required")
  private Integer page;

  private int start;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for count.
   *
   * @return  int
   */
  public Integer getCount() {
    return count;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for page.
   *
   * @return  int
   */
  public Integer getPage() {
    return page;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for start.
   *
   * @return  int
   */
  public int getStart() {
    return start;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for count.
   *
   * @param  count  int
   */
  public void setCount(Integer count) {
    this.count = count;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for page.
   *
   * @param  page  int
   */
  public void setPage(Integer page) {
    this.page = page;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for start.
   *
   * @param  start  int
   */
  public void setStart(int start) {
    this.start = start;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  public void initPage() throws InvalidParamsException {
    if (this.getPage() == null || this.getCount() == null || this.getPage() < 1 || this.getCount() < 1) {
      throw new InvalidParamsException("Invalid page params", ErrorCode.INVALID_PAGE, HttpStatus.BAD_REQUEST);
    } else {
      this.setStart((this.getPage() - 1) * this.getCount());
    }
  }
} // end class PageParams
