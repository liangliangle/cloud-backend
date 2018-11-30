package com.lianglianglee.cloud.common.dto;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class BaseDto {
  @ApiModelProperty("主键 id")
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
