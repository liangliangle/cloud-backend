package com.lianglianglee.cloud.user.dto.create;

import com.lianglianglee.cloud.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

public class GroupUserCreateDto extends BaseDto {
  @ApiModelProperty("用户名")
  private String userName;
  @ApiModelProperty("用户ID")
  private Long userId;
  @ApiModelProperty("小组ID")
  private Long groupId;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }
}
