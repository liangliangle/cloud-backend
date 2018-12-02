package com.lianglianglee.cloud.edit.config;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
  public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

  private static Logger log = LoggerFactory.getLogger(SwaggerConfig.class);


  /**
   * Swagger definition.
   */
  @Bean
  public Docket swaggerSpringfoxDocket() {
    log.debug("Starting Swagger");
    StopWatch watch = new StopWatch();
    watch.start();
    List<Parameter> pars = new ArrayList<Parameter>();
    pars.add(new ParameterBuilder()
            .name("Authorization").description("Authorization")
            .modelRef(new ModelRef("string")).parameterType("header")
            .required(true).build());
    Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
            .genericModelSubstitutes(ResponseEntity.class).select()
            .paths(regex(DEFAULT_INCLUDE_PATTERN)) // and by paths
            .build().globalOperationParameters(pars);
    watch.stop();
    return swaggerSpringMvcPlugin;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("云笔记API")
            .description("一群逗逼朋友")
            .termsOfServiceUrl("")
            .version("1.0").build();


  }
}