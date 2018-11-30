package com.lianglianglee.cloud.edit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
@ComponentScan(basePackages = {"com.lianglianglee"})
@MapperScan("com.lianglianglee.cloud.edit")
public class EditServerApplication {
  public static void main(final String[] args) {
    SpringApplication.run(EditServerApplication.class, args);
  }


}
