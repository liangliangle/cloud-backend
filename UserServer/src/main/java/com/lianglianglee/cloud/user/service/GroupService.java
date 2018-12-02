package com.lianglianglee.cloud.user.service;

import com.lianglianglee.cloud.common.exception.ServiceException;
import com.lianglianglee.cloud.common.interceptor.HttpContext;
import com.lianglianglee.cloud.common.utils.BeanUtils;
import com.lianglianglee.cloud.user.dao.GroupDAO;
import com.lianglianglee.cloud.user.dao.GroupUserDAO;
import com.lianglianglee.cloud.user.dto.create.GroupCreateDto;
import com.lianglianglee.cloud.user.dto.create.GroupUserCreateDto;
import com.lianglianglee.cloud.user.dto.result.GroupDetailDto;
import com.lianglianglee.cloud.user.dto.result.base.GroupDto;
import com.lianglianglee.cloud.user.dto.result.base.GroupUserDto;
import com.lianglianglee.cloud.user.dto.result.base.UserDto;
import com.lianglianglee.cloud.user.entity.GroupEntity;
import com.lianglianglee.cloud.user.entity.GroupUserEntity;
import com.lianglianglee.cloud.user.enums.GroupTypeEnum;
import com.lianglianglee.cloud.user.enums.GroupUserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

  @Autowired
  private GroupDAO groupDAO;
  @Autowired
  private GroupUserDAO groupUserDAO;
  @Autowired
  private UserService userService;


  @Transactional(rollbackFor = ServiceException.class)
  public void insert(GroupCreateDto dto) {
    GroupEntity groupEntity = BeanUtils.dtoToEntity(dto, GroupEntity.class);
    groupEntity.buildDefaultTimeStamp();
    groupEntity.setStatus(1);
    groupDAO.insert(groupEntity);
    GroupUserEntity entity = new GroupUserEntity();
    entity.setGroupId(groupEntity.getId());
    entity.setUserId(dto.getUserId());
    entity.setType(GroupUserTypeEnum.ADMIN.getType());
    entity.setStatus(1);
    entity.setUserName(dto.getUserName());
    entity.buildDefaultTimeStamp();
    groupUserDAO.insert(entity);
  }


  public void update(String newName, Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, true);
    entity.buildDefaultLastTime();
    entity.setName(newName);
    groupDAO.updateById(entity);
  }


  public void delete(Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, true);
    groupDAO.deleteById(id);
  }


  public GroupDetailDto getById(Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, false);
    List<GroupUserEntity> userEntities = groupUserDAO.getByGroupId(id);
    GroupDetailDto detailDto = new GroupDetailDto();
    detailDto.setDto(BeanUtils.entityToDto(entity, GroupDto.class));
    List<GroupUserDto> dtos = new ArrayList<GroupUserDto>(userEntities.size());
    for (GroupUserEntity user : userEntities) {
      dtos.add(BeanUtils.entityToDto(user, GroupUserDto.class));
    }
    detailDto.setUsers(dtos);
    return detailDto;
  }


  public List<GroupDto> getByUser(Long userId) {
    List<GroupEntity> entities = groupDAO.getByUserId(userId);
    List<GroupDto> dtos = new ArrayList<>(entities.size());
    for (GroupEntity entity : entities) {
      dtos.add(BeanUtils.entityToDto(entity, GroupDto.class));
    }
    return dtos;
  }


  @Transactional(rollbackFor = ServiceException.class)
  public void conveyGroup(Long id, Long userId) {
    GroupEntity entity = groupDAO.selectById(id);
    if (GroupTypeEnum.PRIVATE.getType().equals(entity.getType())) {
      throw new ServiceException("私有小组无法转让");
    }
    checkRole(entity, true);
    List<GroupUserEntity> oldAdmin = groupUserDAO.getByUserIdAndGroupId(id, entity.getUserId());
    for (GroupUserEntity user : oldAdmin) {
      if (user.getStatus().equals(1)) {
        user.setType(GroupUserTypeEnum.MEMBER.getType());
        user.buildDefaultLastTime();
        groupUserDAO.updateById(user);
      }
    }
    List<GroupUserEntity> newAdmin = groupUserDAO.getByUserIdAndGroupId(id, userId);
    for (GroupUserEntity user : newAdmin) {
      if (user.getStatus().equals(1)) {
        user.setType(GroupUserTypeEnum.ADMIN.getType());
        user.buildDefaultLastTime();
        groupUserDAO.updateById(user);
      }
    }
    entity.setUserId(userId);
    entity.buildDefaultLastTime();
    groupDAO.updateById(entity);
  }


  public void addUserByGroup(GroupUserCreateDto dto) {
    GroupEntity group = groupDAO.selectById(dto.getGroupId());
    checkRole(group, true);
    List<GroupUserEntity> userEntities = groupUserDAO.getByUserIdAndGroupId(dto.getUserId(), dto
      .getGroupId());
    for (GroupUserEntity entity : userEntities) {
      if (entity.getStatus().equals(1)) {
        throw new ServiceException("用户已经加入小组");
      }
      if (entity.getStatus().equals(2)) {
        throw new ServiceException("邀请已发出，不可重复邀请");
      }
    }
    GroupUserEntity userEntity = new GroupUserEntity();
    userEntity.setType(GroupUserTypeEnum.MEMBER.getType());
    userEntity.setGroupId(dto.getGroupId());
    userEntity.setStatus(2);
    userEntity.setUserId(dto.getUserId());
    UserDto user = userService.getById(dto.getUserId());
    if (null == user) {
      throw new ServiceException("用户不存在");
    }
    userEntity.setUserName(user.getName());
    groupUserDAO.insert(userEntity);
  }

  private void checkRole(GroupEntity entity, Boolean update) {
    if (entity == null) {
      throw new ServiceException("笔记不存在");
    }
    Long userId = HttpContext.getContext().getUserId();
    if (!userId.equals(entity.getUserId())) {
      throw new ServiceException("笔记不存在");
    }
    if (update && entity.getName().equals("默认笔记")) {
      throw new ServiceException("默认笔记不允许操作");
    }
  }

}
