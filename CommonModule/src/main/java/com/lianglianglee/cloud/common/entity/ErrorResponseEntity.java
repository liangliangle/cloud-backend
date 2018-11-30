package com.lianglianglee.cloud.common.entity;

/**
 * @ClessName ErrorResponseEntity
 * @Desc TODO
 * @Author liangliang
 * @Date 2018/9/10 10:54
 * @Version 1.0
 */
public class ErrorResponseEntity {

  private int code;
  private String message;


  public ErrorResponseEntity(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
