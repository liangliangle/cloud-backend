package com.lianglianglee.cloud.user.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置拦截器
 * <p>
 * <p>
 * WebMvcConfigurer在springboot 2.0中过时
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

  private Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

  @Override
  protected void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      //这里有问题
      .allowedOrigins("*")
      .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
      .allowCredentials(true);

  }


  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
      .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
      .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
