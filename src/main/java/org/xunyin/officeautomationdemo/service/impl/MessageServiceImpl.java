package org.xunyin.officeautomationdemo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.MessageMapper;
import org.xunyin.officeautomationdemo.pojo.Message;
import org.xunyin.officeautomationdemo.pojo.User;
import org.xunyin.officeautomationdemo.service.DepartmentService;
import org.xunyin.officeautomationdemo.service.MessageService;
import org.xunyin.officeautomationdemo.service.UserService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserService userService;
    @Autowired
    DepartmentService departmentService;

    @Override
    public void add(Message message) {
        User user = userService.findById(StpUtil.getLoginIdAsInt());
        LocalDateTime now = LocalDateTime.now();
        message.setCreateDate(now);
        message.setReceiveDepartmentId(user.getDepartment());
        messageMapper.add(message);
    }


    @Override
    public List<Message>  list(){
        User user = userService.findById(StpUtil.getLoginIdAsInt());
        return messageMapper.list(user.getDepartment());
    }
}
