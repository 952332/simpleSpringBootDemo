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
 * 公众号推送：(PublicNumberPush)表实体类
 *
 */
@TableName("`public_number_push`")
@Data
@EqualsAndHashCode(callSuper = false)
public class PublicNumberPush implements Serializable {

    // PublicNumberPush编号
    @TableId(value = "public_number_push_id", type = IdType.AUTO)
    private Integer public_number_push_id;

    // 公众号名称
    @TableField(value = "`public_number_name`")
    private String public_number_name;
    // 公众号类型
    @TableField(value = "`public_number_type`")
    private String public_number_type;
    // 普通用户
    @TableField(value = "`ordinary_user`")
    private Integer ordinary_user;
    // 推送时间
    @TableField(value = "`push_time`")
    private Timestamp push_time;
    // 推送内容
    @TableField(value = "`push_content`")
    private String push_content;























	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
