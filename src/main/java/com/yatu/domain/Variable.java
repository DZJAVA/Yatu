package com.yatu.domain;

import java.math.BigDecimal;

import java.util.Date;


/**
 * Created by Yang Wang on 6/15/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/15/2016 10:29
 */
public class Variable {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private Long   businessContextId;
  private String businessDataType;
  private String category;
  private String context;
  private String dataType;
  private String description;
  private String displayName;
  private String expression;
  private Long   id;
  private String name;
  private String params;
  private Long   portfolioId;
  private String valueFormat;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for business context id.
   *
   * @return  Long
   */
  public Long getBusinessContextId() {
    return businessContextId;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for business data type.
   *
   * @return  String
   */
  public String getBusinessDataType() {
    return businessDataType;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for category.
   *
   * @return  String
   */
  public String getCategory() {
    return category;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for class type.
   *
   * @return  Class
   */
  public Class getClassType() {
    if ("Integer".equals(dataType) || "java.lang.Integer".equals(dataType)) {
      return Integer.class;
    } else if ("String".equals(dataType) || "java.lang.String".equals(dataType)) {
      return String.class;
    } else if ("Long".equals(dataType) || "java.lang.Long".equals(dataType)) {
      return Long.class;
    } else if ("Boolean".equals(dataType) || "java.lang.Boolean".equals(dataType)) {
      return Boolean.class;
    } else if ("Date".equals(dataType) || "java.util.Date".equals(dataType)) {
      return Date.class;
    } else if ("BigDecimal".equals(dataType) || "java.math.BigDecimal".equals(dataType)) {
      return BigDecimal.class;
    }

    return null;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for context.
   *
   * @return  String
   */
  public String getContext() {
    return context;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for data type.
   *
   * @return  String
   */
  public String getDataType() {
    return dataType;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for description.
   *
   * @return  String
   */
  public String getDescription() {
    return description;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for display name.
   *
   * @return  String
   */
  public String getDisplayName() {
    return displayName;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for expression.
   *
   * @return  String
   */
  public String getExpression() {
    return expression;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for id.
   *
   * @return  Long
   */
  public Long getId() {
    return id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for name.
   *
   * @return  String
   */
  public String getName() {
    return name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for params.
   *
   * @return  String
   */
  public String getParams() {
    return params;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for portfolio id.
   *
   * @return  Long
   */
  public Long getPortfolioId() {
    return portfolioId;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for value format.
   *
   * @return  String
   */
  public String getValueFormat() {
    return valueFormat;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for business context id.
   *
   * @param  businessContextId  Long
   */
  public void setBusinessContextId(Long businessContextId) {
    this.businessContextId = businessContextId;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for business data type.
   *
   * @param  businessDataType  String
   */
  public void setBusinessDataType(String businessDataType) {
    this.businessDataType = businessDataType;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for category.
   *
   * @param  category  String
   */
  public void setCategory(String category) {
    this.category = category;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for context.
   *
   * @param  context  String
   */
  public void setContext(String context) {
    this.context = context;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for data type.
   *
   * @param  dataType  String
   */
  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for description.
   *
   * @param  description  String
   */
  public void setDescription(String description) {
    this.description = description;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for display name.
   *
   * @param  displayName  String
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for expression.
   *
   * @param  expression  String
   */
  public void setExpression(String expression) {
    this.expression = expression;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for id.
   *
   * @param  id  Long
   */
  public void setId(Long id) {
    this.id = id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for name.
   *
   * @param  name  String
   */
  public void setName(String name) {
    this.name = name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for params.
   *
   * @param  params  String
   */
  public void setParams(String params) {
    this.params = params;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for portfolio id.
   *
   * @param  portfolioId  Long
   */
  public void setPortfolioId(Long portfolioId) {
    this.portfolioId = portfolioId;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for value format.
   *
   * @param  valueFormat  String
   */
  public void setValueFormat(String valueFormat) {
    this.valueFormat = valueFormat;
  }
} // end class Variable
