package com.lianglianglee.cloud.user.dto.result.base;

import com.lianglianglee.cloud.common.dto.BaseDto;

/**
 * u_group_user liangliang
 *
 * @author
 */
public class GroupUserDto extends BaseDto {

  /**
   * 用户ID
   */
  private Long userId;

  /**
   * 小组ID
   */
  private Long groupId;

  /**
   * 类型
   */
  private String type;

  /**
   * 状态
   */
  private Integer status;


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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}