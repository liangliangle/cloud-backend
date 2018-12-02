package com.lianglianglee.cloud.user.dao;

import com.lianglianglee.cloud.user.entity.GroupUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GroupUserDAO继承基类
 */
@Mapper
public interface GroupUserDAO {

  GroupUserEntity selectById(Long id);

  int deleteById(Long id);

  int insert(GroupUserEntity entity);

  int updateById(GroupUserEntity entity);


  List<GroupUserEntity> getByGroupId(Long groupId);


  List<GroupUserEntity> getByUserIdAndGroupId(@Param("userId") Long userId,
                                              @Param("groupId") Long groupId);

}