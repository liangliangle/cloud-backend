package com.lianglianglee.cloud.file.service.oss.exception;

/**
 * OSS异常.
 *
 * @author liangliang
 * @date 2018年4月19日
 */
public class OssException extends RuntimeException {

  public OssException() {
    super();
  }

  public OssException(String message) {
    super(message);
  }

  public OssException(String message, Throwable cause) {
    super(message, cause);
  }

  public OssException(Throwable cause) {
    super(cause);
  }


}
