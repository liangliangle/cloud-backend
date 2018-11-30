package com.lianglianglee.cloud.common.exception;

/**
 * @author 李亮亮
 */
public class ValidationException extends BaseException {

  public ValidationException() {
  }

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(Throwable cause) {
    super(cause);
  }

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ValidationException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
