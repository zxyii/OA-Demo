package org.xunyin.officeautomationdemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xunyin.officeautomationdemo.pojo.*;
import org.xunyin.officeautomationdemo.service.*;
import org.xunyin.officeautomationdemo.util.Result;
//import org.xunyin.officeautomationdemo.service.impl.MessageSenderServiceImpl;

import java.util.List;
@Slf4j
@SaCheckLogin
@RestController
@RequestMapping("/dpt")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    MessageService messageService;
    @Autowired
    EntrySubmitService entrySubmitService;
    @Autowired
    EntryCheckService entryCheckService;
    @Autowired
    UserService userService;
    @Autowired
    ShiftSubmitService shiftSubmitService;
    @Autowired
    ShiftCheckService shiftCheckService;
    @Autowired
    VacationSubmitService vacationSubmitService;
    @Autowired
    VacationCheckService vacationCheckService;


    @SaCheckRole("admin")
    @PostMapping("/add")
    public Result addDpt(@RequestBody Department department){
        log.info("新增部门");
        departmentService.add(department);
        return Result.success("新增部门",department);
    }

    @SaCheckRole({"admin","leader"})
    @GetMapping("/searchMember")
    public Result searchMember(){
        log.info("查询当前部门成员");
        List<String> memberList = departmentService.searchMember();
        return Result.success(memberList);
    }

    @GetMapping("/messageInfo")
    public List<Message> messagesList(){
        log.info("查询当前部门公告");
        return messageService.list();
    }

    @SaCheckRole({"admin","leader"})
    @PostMapping("/addMessage")
    public Result publishMessage(@RequestBody Message message){
        log.info("发布部门公告");
        messageService.add(message);
        return Result.success("公告发布成功： ",message);
    }

    @RequestMapping("/join")
    public Result join(){
        log.info("加入部门");
        int userId = StpUtil.getLoginIdAsInt();
        User user = userService.findById(userId);
        EntrySubmit entrySubmit = entrySubmitService.listInfo2(userId); //通过当前登陆者姓名查询提交的申请信息
        EntryCheck entryCheck = entryCheckService.check(entrySubmit.getId()); //通过申请编号查询该申请的审核信息
        if(entryCheck.getStatus() == 0){
            return Result.success("审核未完成，请等候通知");
        }else if(entryCheck.getStatus() == 1 && entryCheck.getResult() == 0){
            departmentService.entry(entrySubmit.getIdealDepartmentId());
            return Result.success("成功加入该部门");
        }else {
            return Result.error("申请加入失败，说明： " + entryCheck.getDescription());
        }
    }

    @RequestMapping("/shift")
    public Result shift(){
        int userId = StpUtil.getLoginIdAsInt();
        User user = userService.findById(userId);
        ShiftSubmit shiftSubmit = shiftSubmitService.listInfo(user.getUserId());
        ShiftCheck shiftCheck = shiftCheckService.check(shiftSubmit.getId());
        if(shiftCheck.getOrder() == 1 && shiftCheck.getPass() == 1){
            shiftCheck.setResult(1);
            shiftCheck.setStatus(2);
            return Result.error("转部门结果：" + shiftCheck.getResult() +
                                "说明：" + shiftCheck.getDescription());
        }
        else if(shiftCheck.getOrder() == 2 && shiftCheck.getPass() == 1){
            shiftCheck.setResult(1);
            shiftCheck.setStatus(2);
            return Result.error("转部门结果：" + shiftCheck.getResult() +
                                "说明：" + shiftCheck.getDescription());
        }else if(shiftCheck.getOrder() == 2 && shiftCheck.getPass() == 0){
            shiftCheck.setResult(0);
            shiftCheck.setStatus(2);
            user.setDepartment(shiftSubmit.getIdealDepartmentId());
            return Result.success("转部门成功");
        }
        shiftCheck.setStatus(1);
        return Result.error("审核未完成，请等候通知");
    }

    @RequestMapping("/vacation")
    public Result vacation(){
        int userId = StpUtil.getLoginIdAsInt();
        User user = userService.findById(userId);
        VacationSubmit vacationSubmit = vacationSubmitService.listInfo(user.getUserId());
        VacationCheck vacationCheck = vacationCheckService.check(vacationSubmit.getId());
        if(vacationCheck.getStatus() == 0){
            return Result.success("审批未完成，请等候通知");
        }
        else if(vacationCheck.getStatus() == 1 && vacationCheck.getResult() == 0){
            return Result.success("请假成功");
        }
        else if (vacationCheck.getStatus() == 1 && vacationCheck.getResult() == 1){
            return Result.error("请假失败,说明：" + vacationCheck.getDescription());
        }else {
            return Result.error("请假被驳回，请修改原因并重新提交");
        }
    }

}
