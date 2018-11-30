package com.lianglianglee.cloud.common.dto;

import java.util.List;

/**
 * 分页查找的Dto
 *
 * @param <T>
 * @author liangliang
 * @date 2018年9月1日
 */
public class PageDto<T> {

  private Long total;
  private List<T> list;


  public PageDto() {

  }

  public PageDto(Long total, List<T> list) {
    this.list = list;
    this.total = total;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }
}
