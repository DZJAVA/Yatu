package com.yatu.domain;

/**
 * Created by hjf_mac on 9/20/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  09/20/2016 15:45
 */
public class ApplicationConfig {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String configName;

  private String configValue;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new ApplicationConfig object.
   */
  public ApplicationConfig() { }

  /**
   * Creates a new ApplicationConfig object.
   *
   * @param  configName   String
   * @param  configValue  String
   */
  public ApplicationConfig(String configName, String configValue) {
    this.configName  = configName;
    this.configValue = configValue;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for config name.
   *
   * @return  String
   */
  public String getConfigName() {
    return configName;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for config value.
   *
   * @return  String
   */
  public String getConfigValue() {
    return configValue;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for config name.
   *
   * @param  configName  String
   */
  public void setConfigName(String configName) {
    this.configName = configName;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for config value.
   *
   * @param  configValue  String
   */
  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }
} // end class ApplicationConfig
