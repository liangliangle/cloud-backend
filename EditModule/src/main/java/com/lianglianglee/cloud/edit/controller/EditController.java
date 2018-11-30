package com.lianglianglee.cloud.edit.controller;

import com.lianglianglee.cloud.common.dto.PageDto;
import com.lianglianglee.cloud.common.utils.Check;
import com.lianglianglee.cloud.edit.dto.create.EditCreateDto;
import com.lianglianglee.cloud.edit.dto.result.EditDetailDto;
import com.lianglianglee.cloud.edit.dto.result.ShareDto;
import com.lianglianglee.cloud.edit.dto.result.base.EditDto;
import com.lianglianglee.cloud.edit.service.EditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edit")
@Api(value = "无效接口", consumes = "application/json")
public class EditController  {
  @Autowired
  private EditService editService;

  @PostMapping("")
  @ApiOperation("创建笔记")
  public void createEdit(@RequestBody EditCreateDto dto) {
    Check.checkEmpty(dto);
    editService.createEdit(dto);
  }

  @PutMapping("{id}")
  @ApiOperation("修改笔记")
  public void updateEdit(@RequestBody EditCreateDto dto, @PathVariable("id") Long id) {
    Check.checkEmpty(dto);
    editService.updateEdit(dto, id);
  }

  @GetMapping("{id}")
  @ApiOperation("按照ID查看笔记")
  public EditDetailDto getById(@PathVariable("id") Long id) {
    return editService.getById(id);
  }

  @GetMapping("log/{id}")
  @ApiOperation("查看日志，笔记ID")
  public PageDto getLog(@PathVariable("id") Long id,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "row", defaultValue = "10", required = false) int row) {
    //todo 查看笔记修改日志
    return null;
  }

  @GetMapping("log/detail/{id}")
  @ApiOperation("查看日志，日志ID")
  public EditDetailDto getLogDetail(@PathVariable("id") Long id) {
    //todo 查看笔记日志详情
    return null;
  }

  @GetMapping("share/{id}")
  @ApiOperation("分享笔记")
  public ShareDto share(@PathVariable("id") Long id, @RequestParam("encry") boolean encry) {
    //todo 分享笔记
    return null;
  }
@GetMapping("group/{id}")
@ApiOperation("按照小组获取笔记列表")
  public List<EditDto> getByGroup(@PathVariable Long id){
    return editService.getByGroup(id);
  }


}
