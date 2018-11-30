package com.lianglianglee.cloud.user.controller;

import com.lianglianglee.cloud.common.interceptor.HttpContext;
import com.lianglianglee.cloud.common.utils.Check;
import com.lianglianglee.cloud.user.dto.create.GroupCreateDto;
import com.lianglianglee.cloud.user.dto.create.GroupUserCreateDto;
import com.lianglianglee.cloud.user.dto.result.GroupDetailDto;
import com.lianglianglee.cloud.user.dto.result.base.GroupDto;
import com.lianglianglee.cloud.user.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@Api(value = "小组相关接口", consumes = "application/json")
public class GroupController {
  @Autowired
  private GroupService groupService;


  private static Logger logger = LoggerFactory.getLogger(GroupController.class);

  @PostMapping("")
  @ApiOperation("创建小组")
  public void create(@RequestBody GroupCreateDto dto) {
    dto.setUserName(HttpContext.getContext().getUserName());
    dto.setUserId(HttpContext.getContext().getUserId());
    Check.checkEmpty(dto);
    groupService.insert(dto);
  }

  @GetMapping("{id}")
  @ApiOperation("获取小组详情")
  public GroupDetailDto getById(@PathVariable("id") Long id) {

    return groupService.getById(id);
  }

  @GetMapping("/user/{userId}")
  @ApiOperation("获取用户所在小组")
  public List<GroupDto> getByUser(@PathVariable("userId") Long userId) {
    return groupService.getByUser(userId);
  }

  @PutMapping("rename/{id}")
  @ApiOperation("修改小组信息")
  public void update(@RequestParam("newName") String newName, @PathVariable("id") Long id) {

    groupService.update(newName, id);

  }

  @PutMapping("convey/{id}")
  @ApiOperation("转让小组")
  public void conveyGroup(@PathVariable("id") Long id, @RequestParam("userId") Long userId) {
    groupService.conveyGroup(id, userId);
  }

  @PostMapping("user")
  @ApiOperation("邀请用户到小组")
  public void addUserByGroup(@RequestBody GroupUserCreateDto dto) {
    groupService.addUserByGroup(dto);
  }

  @PutMapping("{id}")
  @ApiOperation("同意或拒绝加入小组")
  public void submitToGroup(@PathVariable("id") Long id, @RequestParam("status") Boolean status) {
    //TODO  同意或拒绝加入小组
  }

}
