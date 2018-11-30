package com.lianglianglee.cloud.common.model;

/**
 * @author 李亮亮
 * token的信息
 */
public class EditJwtModel {

  private String userName;
  private Long id;
  private String currentRole;
  private String deviceType;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCurrentRole() {
    return currentRole;
  }

  public void setCurrentRole(String currentRole) {
    this.currentRole = currentRole;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }
}
