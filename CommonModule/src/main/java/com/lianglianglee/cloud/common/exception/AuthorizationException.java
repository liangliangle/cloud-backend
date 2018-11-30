package com.lianglianglee.cloud.common.exception;

/**
 * @author 李亮亮
 * 登陆验证异常
 */
public class AuthorizationException extends BaseException {


  public AuthorizationException() {
  }

  public AuthorizationException(String message) {
    super(message);
  }

  public AuthorizationException(Throwable cause) {
    super(cause);
  }

  public AuthorizationException(String message, Throwable cause) {
    super(message, cause);
  }

  public AuthorizationException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
