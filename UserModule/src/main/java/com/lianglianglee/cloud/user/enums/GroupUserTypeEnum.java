package com.lianglianglee.cloud.user.enums;

import com.lianglianglee.cloud.common.exception.ValidationException;

public enum GroupUserTypeEnum {
  ADMIN("管理"), MEMBER("成员");

  private String type;

  private GroupUserTypeEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public GroupUserTypeEnum valuesOf(String type) {
    for (GroupUserTypeEnum typeEnum : GroupUserTypeEnum.values()) {
      if (typeEnum.type.equals(type)) {
        return typeEnum;
      }
    }
    throw new ValidationException("未知小组类型");
  }
}
