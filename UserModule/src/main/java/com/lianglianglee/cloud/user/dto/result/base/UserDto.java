package com.lianglianglee.cloud.user.dto.result.base;

import com.lianglianglee.cloud.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * u_user
 *
 * @author liangliang
 */
public class UserDto extends BaseDto {

  /**
   * 用户名
   */
  @ApiModelProperty("用户名称")
  private String name;

  /**
   * 邮箱
   */
  @ApiModelProperty("邮箱")
  private String email;
  @ApiModelProperty("手机号")
  private String phone;

  @ApiModelProperty("是否二次验证")
  private boolean auth;

  private String img = "https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png";

  /**
   * 状态
   */
  @ApiModelProperty("状态，1：正常，2异常")
  private Integer status;

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


  public boolean isAuth() {
    return auth;
  }

  public void setAuth(boolean auth) {
    this.auth = auth;
  }
}