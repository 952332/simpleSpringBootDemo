package com.project.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 操作日志：(OperationLog)表实体类
 *
 * @author xxx
 *@since 202X-XX-XX
 */
@TableName("operation_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class OperationLog implements Serializable {

    private static final long serialVersionUID = -48157238791857969L;

    /**
     * 操作日志ID：
     */
    @TableId(value = "operation_log_id", type = IdType.AUTO)
    private Integer operationLogId;

    /**
     * 用户角色：
     */
    @TableField(value = "user_group")
    private String userGroup;
	
	/**
	 * 用户账号：
	 */
	@TableField(value = "user_name")
	private String userName;
	
	/**
	 * 模块名称：
	 */
	@TableField(value = "routes")
	private String routes;

    /**
     * 创建时间：
     */
    @TableField(value = "create_time")
    private Timestamp createTime;

    /**
     * 更新时间：
     */
    @TableField(value = "update_time")
    private Timestamp updateTime;
}

