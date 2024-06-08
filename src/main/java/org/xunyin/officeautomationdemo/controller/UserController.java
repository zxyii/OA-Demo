package org.xunyin.officeautomationdemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xunyin.officeautomationdemo.pojo.*;
import org.xunyin.officeautomationdemo.service.*;
import org.xunyin.officeautomationdemo.util.Result;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@SaCheckLogin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EntrySubmitService entrySubmitService;
    @Autowired
    EntryCheckService entryCheckService;
    @Autowired
    ShiftSubmitService shiftSubmitService;
    @Autowired
    ShiftCheckService shiftCheckService;
    @Autowired
    VacationSubmitService vacationSubmitService;
    @Autowired
    VacationCheckService vacationCheckService;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    RoleService roleService;


    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @GetMapping("/list")
    public Result userList(){
        log.info("查询所有用户：");
        List<User> userList = userService.listAll();
        return Result.success(userList);
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @GetMapping("/findById")
    public Result userInfo1(int userId){
        log.info("根据id查询用户");
        User user = userService.findById(userId);
        return Result.success(user);
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @GetMapping("/findByName")
    public Result userInfo2(String userName){
        log.info("根据用户名查询用户");
        User user = userService.findByName(userName);
        return Result.success(user);
    }

    @SaCheckRole({"admin"})
    @DeleteMapping("/deleteByName")
    public Result deleteUser2(String userName){
        log.info("根据用户名删除用户");
        userService.deleteUserByName(userName);
        return Result.success("删除成功");
    }

    @SaIgnore
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        log.info("用户注册");
        return  userService.register(user);
    }


    @GetMapping("/info")
    public Result userInfo(){
        log.info("查询当前用户信息");
        User user = userService.userInfo();
        return Result.success("个人信息：" ,user);
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(String newPassword){
        log.info("更改密码");
        return userService.updatePwd(newPassword);
    }

    @SneakyThrows
    @PostMapping("/updateImage")
    public Result updateImage(@RequestParam("File") MultipartFile multipartFile){
        log.info("更新头像");
        String fileUrl = fileUploadService.fileUpload(multipartFile);
        return userService.updateImage(fileUrl);
    }

    @PostMapping("/updateRole")
    public Result updateRole(String userName, int roleId){
        log.info("更新用户权限");
        roleService.updateRole(userName,roleId);
        return Result.success("更新成功");
    }

    @GetMapping("/searchDpt")
    public Result searchDpt(){
        log.info("查询当前用户部门信息");
        return userService.searchDpt();
    }


    /*
     关于部门的操作
    */

    @PostMapping("/apply")
    public Result applyDpt(@RequestBody EntrySubmit entrySubmit){
        log.info("申请加入部门");
        return entrySubmitService.add(entrySubmit); //提交申请
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @GetMapping("/applyList")
    public Result applyInfo(){
        log.info("查询申请加入当前部门的所有申请的具体信息");
        List<EntrySubmit> applyList = entrySubmitService.list();
        return Result.success("申请加入本部门的待处理申请： ",applyList);
    }

    @PostMapping("/entryCheckAdd")
    public Result checkAdd(int entryId){
        User leader = userService.findById(StpUtil.getLoginIdAsInt());
        entryCheckService.initialize(entryId, leader.getUserName());
        return Result.success("审核已提交");
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @PostMapping("/entryCheck")
    public Result applyCheck(@NonNull Integer entryId, Integer status, Integer result, String description){
        log.info("审核加入部门的申请");
        EntryCheck entryCheck = entryCheckService.check(entryId);
        entryCheckService.updateResult(entryCheck.getId(),status,result,description);
        return Result.success("处理完成");
    }

    @PostMapping("/shift")
    public Result shiftDpt(@RequestBody ShiftSubmit shiftSubmit){
        log.info("申请转部门");
        return shiftSubmitService.add(shiftSubmit);
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @GetMapping("/shiftList")
    public Result shiftList(){
        log.info("查询申所有转部门的申请的具体信息");
        List<ShiftSubmit> shiftList = shiftSubmitService.listAll();
        return Result.success(shiftList);
    }

    @PostMapping("/shiftCheckAdd")
    public Result shiftCheckAdd(int shiftId){
        User leader = userService.findById(StpUtil.getLoginIdAsInt());
        shiftCheckService.initialize(shiftId,leader.getUserName());
        return Result.success("审核已提交");
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @PostMapping("/shiftCheck")
    public Result  shiftCheck(@NotNull Integer shiftId, Integer order, Integer pass, String description){
        log.info("审核转部门");
        ShiftCheck shiftCheck = shiftCheckService.check(shiftId);
        shiftCheckService.update(shiftCheck.getId(),order,pass,description);
        return Result.success("审核完毕");
    }


    @PostMapping("/vacation")
    public Result vacationApply(@RequestBody VacationSubmit vacationSubmit){
        log.info("请假");
        return vacationSubmitService.add(vacationSubmit);
    }

    @PostMapping("/vacationUpdate")
    public Result vacationUpdate(String content){
        log.info("修改请假理由");
        return vacationSubmitService.updateReason(content);
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @GetMapping("/vacationList")
    public Result vacationList(){
        log.info("查询所有请假的具体信息");
        List<VacationSubmit> vacationList = vacationSubmitService.listAll();
        return Result.success("请假申请：",vacationList);
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @PostMapping("/vacationCheckAdd")
    public Result vacationCheckAdd(int vacationId){
        User leader = userService.findById(StpUtil.getLoginIdAsInt());
        vacationCheckService.initialize(vacationId, leader.getUserName());
        return Result.success("审核已提交");
    }

    @SaCheckRole(value = {"admin","leader"},mode = SaMode.OR)
    @PostMapping("/vacationCheck")
    public Result vacationCheck(int vacationId,int status,int result,String description){
        log.info("审核请假");
        VacationCheck vacationCheck = vacationCheckService.check(vacationId);
        vacationCheckService.updateResult(vacationCheck.getId(),status,result,description);
        return Result.success("处理完毕");
    }



}
