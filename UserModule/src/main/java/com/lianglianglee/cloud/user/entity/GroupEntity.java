package com.lianglianglee.cloud.user.entity;

import com.lianglianglee.cloud.common.entity.BaseEntity;

/**
 * u_group
 *
 * @author liangliang
 */
public class GroupEntity extends BaseEntity {

  private String name;

  /**
   * 公开/私有
   */
  private String type;

  private Long userId;

  private Integer status;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}