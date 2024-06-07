package org.xunyin.officeautomationdemo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.util.SaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xunyin.officeautomationdemo.service.LoginService;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    LoginService loginService;
    @RequestMapping("login")
    public SaResult login(String userName, String password){
        return loginService.doLogin(userName, password);
    }

    @RequestMapping("checkLogin")
    public SaResult checkLogin(){
        return loginService.checkLogin();
    }

    @SaCheckPermission(orRole = {"super_admin","department_admin"})
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo(){
        return loginService.tokenInfo();
    }

    @RequestMapping("logout")
    public SaResult logout(){
        return loginService.logout();
    }
}