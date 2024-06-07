package org.xunyin.officeautomationdemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.service.RoleService;

@RestController
@SaCheckLogin
@SaCheckRole("admin")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/update")
    public Result updateRole(@RequestParam String userName,@RequestParam int roleId){
        roleService.updateRole(userName,roleId);
        return Result.success(userName + "角色更新成功");
    }

}