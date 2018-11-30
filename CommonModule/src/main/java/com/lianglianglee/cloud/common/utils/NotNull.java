package com.lianglianglee.cloud.common.utils;

import java.lang.annotation.*;

/**
 * @Desc 非空注解
 * @Author liangliang
 * @Date 2018/8/30 15:37
 * @Version 1.0
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

  String message() default "有字段";


}
