package org.xunyin.officeautomationdemo.controller;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xunyin.officeautomationdemo.pojo.Group;
import org.xunyin.officeautomationdemo.service.GroupService;
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
    @Autowired
    GroupService groupService;

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @PostMapping("/createGroup")
    public Result createGroup(@RequestBody Group group){
        log.info("创建工作小组");
        workService.createGroup(group);
        return Result.success(group.getGroupName() + "创建成功");
    }
    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @PostMapping(value = "/inviteMember", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public Result inviteMember(@NotNull Integer groupId, @RequestParam List<String> users){
        log.info("邀请小组成员");
        workService.inviteMember(groupId,users);
        return workService.inviteMember(groupId,users);
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @GetMapping("/listMember")
    public Result listMember(){
        log.info("查看小组成员");
        List<String> users = groupService.listMember();
        return Result.success("成员列表： ",users);
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
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
        return Result.success("当前进度：",progress);
    }

    @PostMapping("/end")
    public Result endWork(){
        log.info("结束工作");
        return workService.endWork();
    }
}
