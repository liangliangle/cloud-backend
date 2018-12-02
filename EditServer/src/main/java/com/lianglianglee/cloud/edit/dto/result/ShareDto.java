package com.lianglianglee.cloud.edit.dto.result;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分享的url和连接，公开分享，密码为空
 */
public class ShareDto {
  @ApiModelProperty("分享链接")
  private String url;
  @ApiModelProperty("密码，如果有的话")
  private String password;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
