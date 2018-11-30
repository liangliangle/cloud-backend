package com.lianglianglee.cloud.edit.service;

import com.lianglianglee.cloud.common.dto.PageDto;
import com.lianglianglee.cloud.common.exception.ServiceException;
import com.lianglianglee.cloud.common.interceptor.HttpContext;
import com.lianglianglee.cloud.common.utils.BeanUtils;
import com.lianglianglee.cloud.edit.dao.EditDAO;
import com.lianglianglee.cloud.edit.dao.EditInfoDAO;
import com.lianglianglee.cloud.edit.dao.EditLogDAO;
import com.lianglianglee.cloud.edit.dto.create.EditCreateDto;
import com.lianglianglee.cloud.edit.dto.result.EditDetailDto;
import com.lianglianglee.cloud.edit.dto.result.ShareDto;
import com.lianglianglee.cloud.edit.dto.result.base.EditDto;
import com.lianglianglee.cloud.edit.entity.EditEntity;
import com.lianglianglee.cloud.edit.entity.EditInfoEntity;
import com.lianglianglee.cloud.edit.entity.EditLogEntity;
import com.lianglianglee.cloud.edit.enums.LogTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditService {
  @Autowired
  private EditDAO editDAO;
  @Autowired
  private EditLogDAO logDAO;
  @Autowired
  private EditInfoDAO infoDAO;


  private static Logger logger = LoggerFactory.getLogger(EditService.class);


  @Transactional(rollbackFor = ServiceException.class)
  public void createEdit(EditCreateDto dto) {
    Long userId = HttpContext.getContext().getUserId();
    String userName = HttpContext.getContext().getUserName();
    logger.info("insert:userId:{},parentId:{},content:{}", userId, dto.getParentId(), dto.getContent());
    EditEntity entity = new EditEntity();
    entity.setGroupId(dto.getGroupId());
    entity.setParentId(dto.getParentId());
    entity.setTitle(dto.getTitle());
    entity.setStatus(1);
    entity.setUserId(userId);
    entity.setUserName(userName);
    entity.buildDefaultTimeStamp();
    editDAO.insert(entity);
    insert(entity.getId(), dto.getContent());

  }

  @Transactional(rollbackFor = ServiceException.class)
  public void updateEdit(EditCreateDto dto, Long id) {
    Long userId = HttpContext.getContext().getUserId();
    String userName = HttpContext.getContext().getUserName();
    logger.info("update:userId:{},parentId:{}", userId, dto.getParentId());
    EditEntity entity = editDAO.selectById(id);
    //todo  请注意小组成员也可以修改
    if (!entity.getUserId().equals(userId)) {
      throw new ServiceException("未找到笔记");
    }
    entity.setTitle(dto.getTitle());
    entity.setParentId(dto.getParentId());
    entity.buildDefaultLastTime();
    editDAO.updateById(entity);
    EditInfoEntity infoEntity = infoDAO.getByEditId(id);
    if (infoEntity.getInfo() == null || !infoEntity.getInfo().equals(dto.getContent())) {
      insert(id, dto.getContent());
    }
  }


  public EditDetailDto getById(Long id) {
    Long userId = HttpContext.getContext().getUserId();
    EditEntity entity = editDAO.selectById(id);
    if (null == entity) {
      throw new ServiceException("未找到笔记");
    }
    if (!entity.getUserId().equals(userId)) {
      throw new ServiceException("未找到笔记");
    }
    EditDetailDto detailDto = BeanUtils.entityToDto(entity, EditDetailDto.class);
    EditInfoEntity infoEntity = infoDAO.getByEditId(id);
    detailDto.setContent(infoEntity.getInfo());
    return detailDto;
  }


  public PageDto getLog(Long id, int page, int row) {
    //todo 查看笔记修改日志
    return null;
  }


  public ShareDto share(Long id, boolean encry) {
    //todo 分享笔记
    return null;
  }


  public EditDetailDto getLogDetail(Long id) {
    //todo 获取笔记日志详情
    return null;
  }


  private void insert(Long editId, String content) {
    logger.info("editId:{},content:{}", editId, content);
    EditInfoEntity oldInfo = infoDAO.getByEditId(editId);
    infoDAO.deleteByEditId(editId);
    LogTypeEnum type = LogTypeEnum.CREATE;
    EditInfoEntity infoEntity = new EditInfoEntity();
    infoEntity.setEditId(editId);
    infoEntity.setInfo(content);
    infoEntity.setStatus(1);
    infoEntity.setType(1);
    infoEntity.buildDefaultTimeStamp();
    infoDAO.insert(infoEntity);
    Long infoId = infoEntity.getId();
    if (null != oldInfo) {
      type = LogTypeEnum.UPDATE;
      infoId = oldInfo.getId();
    }
    saveLog(editId, type, infoId);
  }

  private void saveLog(Long editId, LogTypeEnum type, Long infoId) {
    logger.info("type:{},editId:{},infoId:{}", type, editId, infoId);
    EditLogEntity logEntity = new EditLogEntity();
    logEntity.setInfoId(infoId);
    logEntity.setEditId(editId);
    logEntity.setType(type.getType());
    logEntity.setStatus(1);
    logEntity.buildDefaultTimeStamp();
    logDAO.insert(logEntity);
  }


  public List<EditDto> getByGroup(Long id) {
    List<EditDto> dtos = editDAO.getByGroup(id, 0L);
    getByGroup(dtos);
    return dtos;
  }

  private void getByGroup(List<EditDto> dtos) {
    dtos.forEach(dto -> {
      dto.setChildren(editDAO.getByGroup(dto.getGroupId(), dto.getId()));
    });
  }
}
