package org.xunyin.officeautomationdemo.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.pojo.User;
import org.xunyin.officeautomationdemo.service.LoginService;
import org.xunyin.officeautomationdemo.service.UserService;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Override
    public SaResult doLogin(String userName, String password) {
        User user = userService.findByName(userName);
        if (user == null){
            return SaResult.error("账号不存在或账号名错误");
        }else if(user.getUserName().equals(userName) && SaSecureUtil.md5BySalt(password,user.getSalt()).equals(user.getPassword())){
            StpUtil.login(user.getUserId());
           return SaResult.ok("用户" + user.getUserName() + "登陆成功");
        }else {
            return SaResult.error("登陆失败 请检查密码是否正确");
        }
    }

    @Override
    public SaResult checkLogin() {
        if(StpUtil.isLogin()){
            return SaResult.ok("已登录");
        }
        return SaResult.error("未登录");
    }

    @Override
    public SaResult tokenInfo() {
        return  SaResult.data(StpUtil.getTokenInfo());
    }

    @Override
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok("退出登陆成功");
    }


}
