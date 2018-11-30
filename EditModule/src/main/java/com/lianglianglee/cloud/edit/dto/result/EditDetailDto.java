package com.lianglianglee.cloud.edit.dto.result;

import com.lianglianglee.cloud.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class EditDetailDto extends BaseDto {

  @ApiModelProperty("笔记创建时间")
  private Date createTime;

  @ApiModelProperty("笔记最后修改时间")
  private Date lastUpdateTime;

  /**
   * 创建人
   */
  @ApiModelProperty("创建人")
  private Long userId;

  /**
   * 小组ID
   */
  @ApiModelProperty("所属小组")
  private Long groupId;

  /**
   * 用户名
   */
  @ApiModelProperty("用户名")
  private String userName;

  private String title;

  /**
   * 父ID
   */
  @ApiModelProperty("父页面ID")
  private Long parentId;

  /**
   * 状态
   */
  @ApiModelProperty("状态")
  private Integer status;
  @ApiModelProperty("正文")
  private String content;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
