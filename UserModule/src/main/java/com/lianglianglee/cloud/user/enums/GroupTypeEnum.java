package com.lianglianglee.cloud.user.enums;

import com.lianglianglee.cloud.common.exception.ValidationException;

public enum GroupTypeEnum {
  PRIVATE("私有"), PUBLIC("公有");

  private String type;

  private GroupTypeEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public GroupTypeEnum valuesOf(String type) {
    for (GroupTypeEnum typeEnum : GroupTypeEnum.values()) {
      if (typeEnum.type.equals(type)) {
        return typeEnum;
      }
    }
    throw new ValidationException("未知小组类型");
  }
}
