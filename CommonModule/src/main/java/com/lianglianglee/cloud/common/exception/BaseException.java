package com.lianglianglee.cloud.common.exception;

/**
 * Base exception for project.
 *
 * @author 李亮亮
 */
public class BaseException extends RuntimeException {


  public BaseException() {
  }

  public BaseException(String message) {
    super(message);
  }

  public BaseException(Throwable cause) {
    super(cause);
    cause.printStackTrace();
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
    cause.printStackTrace();
  }

  public BaseException(String message, Throwable cause, boolean enableSuppression,
                       boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    cause.printStackTrace();
  }
}
