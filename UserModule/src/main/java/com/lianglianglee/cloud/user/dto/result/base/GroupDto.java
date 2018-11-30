package com.lianglianglee.cloud.user.dto.result.base;

import com.lianglianglee.cloud.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * u_group
 *
 * @author liangliang
 */
public class GroupDto extends BaseDto {

  private String name;

  /**
   * 公开/私有
   */
  @ApiModelProperty("小组类型")
  private String type;
  @ApiModelProperty("创建人ID")
  private Long userId;
  @ApiModelProperty("小组状态")
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