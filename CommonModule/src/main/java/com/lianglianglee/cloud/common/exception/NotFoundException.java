package com.lianglianglee.cloud.common.exception;

/**
 * @author 李亮亮
 */
public class NotFoundException extends BaseException {

  public NotFoundException() {
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(Throwable cause) {
    super(cause);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
