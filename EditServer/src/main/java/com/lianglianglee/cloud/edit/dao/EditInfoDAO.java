package com.lianglianglee.cloud.edit.dao;

import com.lianglianglee.cloud.edit.entity.EditInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * EditInfoDAO继承基类
 */
@Mapper
public interface EditInfoDAO {
  EditInfoEntity getByEditId(Long id);

  List<EditInfoEntity> getAllByEditId(Long id);

  EditInfoEntity getById(Long id);

  int deleteById(Long id);

  int updateById(EditInfoEntity entity);

  int insert(EditInfoEntity entity);


  int deleteByEditId(Long editId);
}