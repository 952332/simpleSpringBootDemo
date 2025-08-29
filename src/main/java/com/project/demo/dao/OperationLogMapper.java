package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志：Mapper接口
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
}
