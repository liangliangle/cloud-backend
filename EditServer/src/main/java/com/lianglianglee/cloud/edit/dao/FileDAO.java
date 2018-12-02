package com.lianglianglee.cloud.edit.dao;

import com.lianglianglee.cloud.edit.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * FileDAO继承基类
 */
@Mapper
public interface FileDAO {

  FileEntity selectById(Long id);


  int deleteById(Long id);

  int insert(FileEntity entity);

  int updateById(FileEntity entity);
}