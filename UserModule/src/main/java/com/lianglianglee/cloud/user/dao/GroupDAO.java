package com.lianglianglee.cloud.user.dao;

import com.lianglianglee.cloud.user.entity.GroupEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GroupDAO继承基类
 */
@Mapper
public interface GroupDAO {
  GroupEntity selectById(Long id);

  int deleteById(Long id);

  int insert(GroupEntity entity);

  int updateById(GroupEntity entity);


  List<GroupEntity> getByUserId(Long userId);

}