package com.lianglianglee.cloud.user;

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
@MapperScan("com.lianglianglee.cloud.user")
public class UserServerApplication {
  public static void main(final String[] args) {
    SpringApplication.run(UserServerApplication.class, args);
  }


}
