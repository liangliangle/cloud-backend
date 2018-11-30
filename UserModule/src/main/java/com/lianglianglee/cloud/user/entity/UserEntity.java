package com.lianglianglee.cloud.user.entity;

import com.lianglianglee.cloud.common.entity.BaseEntity;

/**
 * u_user
 *
 * @author liangliang
 */
public class UserEntity extends BaseEntity {

  /**
   * 用户名
   */
  private String name;

  /**
   * 邮箱
   */
  private String email;

  private String phone;

  /**
   * 密码
   */
  private String password;

  /**
   * 安全密钥
   */
  private String secret;

  /**
   * 状态
   */
  private Integer status;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}