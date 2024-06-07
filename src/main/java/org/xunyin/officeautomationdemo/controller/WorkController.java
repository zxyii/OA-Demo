package org.xunyin.officeautomationdemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xunyin.officeautomationdemo.pojo.Group;
import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.Work;
import org.xunyin.officeautomationdemo.service.WorkService;

import java.util.List;

@Slf4j
@RestController
@SaCheckLogin
@RequestMapping("/work")
public class WorkController {
    @Autowired
    WorkService workService;

    @SaCheckRole({"admin","leader"})
    @PostMapping("/createGroup")
    public Result createGroup(@RequestBody Group group){
        log.info("创建工作小组");
        workService.createGroup(group);
        return Result.success(group.getGroupName() + "创建成功");
    }
    @SaCheckRole({"admin","leader"})
    @PostMapping("/inviteMemeber")
    public Result inviteMember(List<String> users){
        log.info("邀请小组成员");
        workService.inviteMember(users);
        return Result.success();
    }

    @SaCheckRole({"admin","leader"})
    @PostMapping("/publishWork")
    public Result publishWork(@RequestBody Work work){
        log.info("发布工作内容");
        workService.publishWork(work);
        return Result.success(work);
    }

    @GetMapping("/info")
    public Result workInfo(){
        log.info("查询工作内容");
        Work work = workService.workInfo();
        return Result.success(work);
    }

    @PostMapping("/updateProgress")
    public Result updateProgress(String progress){
        log.info("更新工作进度");
        workService.updateProgress(progress);
        return Result.success();
    }

    @PostMapping("/end")
    public Result endWork(){
        log.info("结束工作");
        return workService.endWork();
    }
}
