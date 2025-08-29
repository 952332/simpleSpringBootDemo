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
 * 提醒通知：(ReminderNotifications)表实体类
 *
 */
@TableName("`reminder_notifications`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ReminderNotifications implements Serializable {

    // ReminderNotifications编号
    @TableId(value = "reminder_notifications_id", type = IdType.AUTO)
    private Integer reminder_notifications_id;

    // 标题名称
    @TableField(value = "`title_name`")
    private String title_name;
    // 普通用户
    @TableField(value = "`ordinary_user`")
    private Integer ordinary_user;
    // 通知日期
    @TableField(value = "`notification_date`")
    private Timestamp notification_date;
    // 通知内容
    @TableField(value = "`notification_content`")
    private String notification_content;























	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
