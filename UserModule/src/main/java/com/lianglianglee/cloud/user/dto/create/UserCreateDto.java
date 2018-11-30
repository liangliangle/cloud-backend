package com.lianglianglee.cloud.user.dto.create;

import com.lianglianglee.cloud.common.dto.BaseDto;
import com.lianglianglee.cloud.common.utils.Validation;
import io.swagger.annotations.ApiModelProperty;

public class UserCreateDto extends BaseDto {

  /**
   * 用户名
   */
  @Validation(notNull = true)
  @ApiModelProperty("用户名称")
  private String name;

  /**
   * 邮箱
   */
  @Validation(notNull = true)
  @ApiModelProperty("密码")
  private String password;
  @Validation(notNull = true)
  @ApiModelProperty("邮箱")
  private String email;
  @Validation(notNull = true)
  @ApiModelProperty("手机号")
  private String phone;

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


}
