package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.MessageSending;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息发送：(MessageSending)Mapper接口
 *
 */
@Mapper
public interface MessageSendingMapper extends BaseMapper<MessageSending>{

}
