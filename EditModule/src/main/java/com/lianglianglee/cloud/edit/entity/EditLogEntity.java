package com.lianglianglee.cloud.edit.entity;

import com.lianglianglee.cloud.common.entity.BaseEntity;

/**
 * edit-log
 *
 * @author
 */
public class EditLogEntity extends BaseEntity {


  /**
   * 正文
   */
  private Long infoId;

  private Long editId;

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

  public Long getEditId() {
    return editId;
  }

  public void setEditId(Long editId) {
    this.editId = editId;
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