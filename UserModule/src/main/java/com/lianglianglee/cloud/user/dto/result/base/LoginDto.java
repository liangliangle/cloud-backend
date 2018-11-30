package com.lianglianglee.cloud.user.dto.result.base;

import com.lianglianglee.cloud.common.dto.BaseDto;

import java.util.List;

public class LoginDto extends BaseDto {

  private UserDto userDto;
  private String tocken;
  private List<GroupDto> groups;

  public UserDto getUserDto() {
    return userDto;
  }

  public void setUserDto(UserDto userDto) {
    this.userDto = userDto;
  }

  public String getTocken() {
    return tocken;
  }

  public void setTocken(String tocken) {
    this.tocken = tocken;
  }

  public List<GroupDto> getGroups() {
    return groups;
  }

  public void setGroups(List<GroupDto> groups) {
    this.groups = groups;
  }
}
