package com.lianglianglee.cloud.edit.enums;

import com.lianglianglee.cloud.common.exception.ValidationException;

public enum LogTypeEnum {
  CREATE("创建"), UPDATE("修改"), SHARE("分享"), DELETE("删除");
  private String type;

  LogTypeEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public LogTypeEnum valuesOf(String type) {
    for (LogTypeEnum typeEnum : LogTypeEnum.values()) {
      if (typeEnum.type.equals(type)) {
        return typeEnum;
      }
    }
    throw new ValidationException("未知日志类型");
  }

  @Override
  public String toString() {
    return type;
  }
}
