package com.yatu.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * Created by Yang Wang on 6/12/16.
 *
 * @author   <a href="mailto:yang.wang@ozstrategy.com">Yang Wang</a>
 * @version  06/12/2016 15:03
 */
@Profile("prod")
@Configuration
@PropertySource(value = "classpath:env.properties")
public class DBConfigProd {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String databaseDriverClassName;
  private String databasePassword;
  private String databaseUrl;
  private String databaseUsername;

  /** DOCUMENT ME! */
  private Environment  env;
  private final Logger logger = LoggerFactory.getLogger(getClass());

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * dataSource.
   *
   * @return  DataSource
   */
  @Bean public DataSource dataSource() {
    HikariDataSource ds = new HikariDataSource();
    ds.setDataSourceClassName(this.databaseDriverClassName);
    ds.addDataSourceProperty("url", this.databaseUrl);
    ds.addDataSourceProperty("user", this.databaseUsername);
    ds.addDataSourceProperty("password", this.databasePassword);
    ds.setConnectionTestQuery("SELECT 1");


    ds.addDataSourceProperty("cachePrepStmts", true);
    ds.addDataSourceProperty("prepStmtCacheSize", 250);
    ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
    ds.addDataSourceProperty("useServerPrepStmts", true);
    ds.setMinimumIdle(5);
    ds.setMaximumPoolSize(50);
    ds.setLeakDetectionThreshold(60000);
    ds.setConnectionTimeout(30000);
    ds.setIdleTimeout(600000);
    ds.setMaxLifetime(1200000);

    return ds;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for env.
   *
   * @param  env  Environment
   */
  @Autowired public void setEnv(Environment env) {
    this.env = env;

    this.databaseDriverClassName = env.getProperty("JDBC_DRIVER");
    logger.info("Using :" + this.databaseDriverClassName);
    this.databaseUrl = env.getProperty("JDBC_URL");
    logger.info("For URL:" + this.databaseUrl);
    this.databaseUsername = env.getProperty("JDBC_USERNAME");
    this.databasePassword = env.getProperty("JDBC_PASSWORD");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * transactionManager.
   *
   * @return  PlatformTransactionManager
   */
  @Bean public PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }
} // end class DBConfig
