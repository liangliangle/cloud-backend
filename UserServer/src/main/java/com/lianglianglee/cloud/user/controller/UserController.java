package com.lianglianglee.cloud.user.controller;

import com.lianglianglee.cloud.user.dto.create.UserCreateDto;
import com.lianglianglee.cloud.user.dto.result.base.LoginDto;
import com.lianglianglee.cloud.user.dto.result.base.UserDto;
import com.lianglianglee.cloud.user.dto.update.UserPasswordDto;
import com.lianglianglee.cloud.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Api(value = "用户接口", consumes = "application/json")
public class UserController {
  @Autowired
  private UserService userService;


  @PostMapping("")
  @ApiOperation("注册用户")
  public void addUser(@RequestBody UserCreateDto dto) {
    userService.insert(dto);
  }

  @GetMapping("login")
  @ApiOperation("登陆")
  public LoginDto login(@RequestHeader("Authorization") String auth) {
    String[] auths = auth.split(" ");
    if (auths.length == 2) {
      return userService.login(auths[0], auths[1]);
    } else {
      return userService.reload(auth);
    }
  }

  @PutMapping("password")
  @ApiOperation("修改密码")
  public void updatePassword(@RequestBody UserPasswordDto dto) {
    userService.updatePassword(dto);
  }

  @PutMapping("")
  @ApiOperation("修改个人信息")
  public void update(@RequestBody UserDto dto) {
    userService.update(dto);
  }

  @GetMapping("code/{id}")
  @ApiOperation("二次验证二维码")
  public String getCode(@PathVariable("id") Long id) {
    return userService.getCodeImg(id);
  }

  @PostMapping("code/{id}")
  @ApiOperation("验证code")
  public void checkCode(@PathVariable("id") Long userId, @RequestParam String code) {
    userService.checkCode(userId, code);
  }


}
