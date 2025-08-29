package com.project.demo.controller;

import com.project.demo.controller.base.BaseController;
import com.project.demo.entity.OperationLog;
import com.project.demo.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志
 */
@RestController
@RequestMapping("operation_log")
public class OperationLogController extends BaseController<OperationLog, OperationLogService> {
    /**
     * 服务对象
     */
    @Autowired
    public OperationLogController(OperationLogService service) {
        setService(service);
    }

}


