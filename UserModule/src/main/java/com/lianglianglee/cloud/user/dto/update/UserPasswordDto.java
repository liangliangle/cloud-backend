package com.lianglianglee.cloud.user.dto.update;

import com.lianglianglee.cloud.common.dto.BaseDto;
import com.lianglianglee.cloud.common.utils.Validation;

public class UserPasswordDto extends BaseDto {
  @Validation(notNull = true)
  private String oldPassword;
  @Validation(notNull = true)
  private String newPassword;
  @Validation(notNull = true)
  private String code;

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
