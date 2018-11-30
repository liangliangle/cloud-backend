package com.lianglianglee.cloud.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileSizeConfig {

  /**
   * 文件上传配置.
   */
  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigElement element = new MultipartConfigElement(
      "", 10240000, 10240000, 0);
    return element;
  }

}
