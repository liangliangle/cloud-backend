package com.lianglianglee.cloud.edit.dto.result.base;

import com.lianglianglee.cloud.common.dto.BaseDto;

/**
 * e_edit_info
 *
 * @author
 */
public class EditInfoDto extends BaseDto {

  private Long editId;

  private Integer type;

  private Integer status;

  /**
   * 正文
   */
  private String info;


  public Long getEditId() {
    return editId;
  }

  public void setEditId(Long editId) {
    this.editId = editId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
}