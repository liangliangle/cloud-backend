package com.lianglianglee.cloud.common.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validation {
  //校验失败报错信息
  String value() default "数据校验失败";

  String[] values() default {};

  boolean notNull() default false;

  //正则表达式
  String pattern() default "";

}