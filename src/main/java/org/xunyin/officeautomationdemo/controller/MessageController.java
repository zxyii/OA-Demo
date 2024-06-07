package org.xunyin.officeautomationdemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xunyin.officeautomationdemo.mapper.MessageMapper;
import org.xunyin.officeautomationdemo.pojo.Message;
import org.xunyin.officeautomationdemo.util.Result;

@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageMapper messageMapper;

    @SaCheckRole({"admin","leader"})
    @PostMapping("/publish")
    public Result publish(@RequestBody Message message){
        messageMapper.add(message);
        return Result.success("公告发送成功");
    }

}
