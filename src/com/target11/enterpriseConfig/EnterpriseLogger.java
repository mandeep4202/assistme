package com.target11.enterpriseConfig;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnterpriseLogger {
  static {
    /**
     * This statement is used to configure the log4j property file
     */
    //PropertyConfigurator.configure("./res");
  }

  /**
   * This factory method provide the logger having log4j properties file
   * configuration
   * @param class Class object
   * @return Logger object
   */
  public static Logger getLogger(Class c) {
    Logger log = LoggerFactory.getLogger(c);
    return log;
  }


}
