package com.project.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 消息发送：(MessageSending)表实体类
 *
 */
@TableName("`message_sending`")
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageSending implements Serializable {

    // MessageSending编号
    @TableId(value = "message_sending_id", type = IdType.AUTO)
    private Integer message_sending_id;

    // 发送用户
    @TableField(value = "`send_user`")
    private Integer send_user;
    // 用户姓名
    @TableField(value = "`user_name`")
    private String user_name;
    // 发送方式
    @TableField(value = "`send_method`")
    private String send_method;
    // 收件用户
    @TableField(value = "`receiving_user`")
    private Integer receiving_user;
    // 收件账号
    @TableField(value = "`receiving_account_number`")
    private String receiving_account_number;
    // 发送时间
    @TableField(value = "`sending_time`")
    private Timestamp sending_time;
    // 内容详情
    @TableField(value = "`content_details`")
    private String content_details;
    // 发送状态
    @TableField(value = "`send_status`")
    private String send_status;























	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
