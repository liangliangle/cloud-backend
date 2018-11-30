package com.lianglianglee.cloud.edit.dto.result.base;

import com.lianglianglee.cloud.common.dto.BaseDto;

/**
 * edit-log
 *
 * @author
 */
public class EditLogDto extends BaseDto {


  private Long infoId;

  /**
   * 类型
   */
  private String type;

  private Integer status;

  public Long getInfoId() {
    return infoId;
  }

  public void setInfoId(Long infoId) {
    this.infoId = infoId;
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