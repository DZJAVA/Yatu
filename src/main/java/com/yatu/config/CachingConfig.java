package com.yatu.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;


/**
 * Created by hjf_mac on 11/1/16.
 *
 * @author   <a href="mailto:jianfeng.huang@ozstrategy.com">Jianfeng Huang</a>
 * @version  11/01/2016 09:47
 */
@Configuration @EnableCaching public class CachingConfig {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * cachceManager.
   *
   * @param   ehcahceFactory  EhCacheManagerFactoryBean
   *
   * @return  EhCacheCacheManager
   */
  @Bean public EhCacheCacheManager cachceManager(EhCacheManagerFactoryBean ehcahceFactory) {
    EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
    ehCacheCacheManager.setCacheManager(ehcahceFactory.getObject());

    return ehCacheCacheManager;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * ehcahceFactory.
   *
   * @return  EhCacheManagerFactoryBean
   */
  @Bean public EhCacheManagerFactoryBean ehcahceFactory() {
    EhCacheManagerFactoryBean ehCache = new EhCacheManagerFactoryBean();
    ehCache.setConfigLocation(new ClassPathResource("ehcache.xml"));
    ehCache.setCacheManagerName("customerCache");
    ehCache.setShared(true);

    return ehCache;
  }

} // end class CachingConfig
