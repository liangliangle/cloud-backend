package com.lianglianglee.cloud.common.exception;

/**
 * 数据访问层exception.
 *
 * @author 李亮亮
 */
public class DaoException extends BaseException {

  public DaoException() {
  }

  public DaoException(String message) {
    super(message);
  }

  public DaoException(Throwable cause) {
    super(cause);
  }

  public DaoException(String message, Throwable cause) {
    super(message, cause);
  }

  public DaoException(String message, Throwable cause, boolean enableSuppression,
                      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
