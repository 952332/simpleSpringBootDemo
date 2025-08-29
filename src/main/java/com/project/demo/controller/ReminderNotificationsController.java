package com.project.demo.controller;

import com.project.demo.entity.ReminderNotifications;
import com.project.demo.service.ReminderNotificationsService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 提醒通知：(ReminderNotifications)表控制层
 *
 */
@RestController
@RequestMapping("/reminder_notifications")
public class ReminderNotificationsController extends BaseController<ReminderNotifications, ReminderNotificationsService> {

    /**
     * 提醒通知对象
     */
    @Autowired
    public ReminderNotificationsController(ReminderNotificationsService service) {
        setService(service);
    }



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }


}
