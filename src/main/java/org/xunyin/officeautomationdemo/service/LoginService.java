package org.xunyin.officeautomationdemo.service;

import cn.dev33.satoken.util.SaResult;

public interface LoginService {
    SaResult doLogin(String userName, String password);
    //查询登录状态
    public SaResult checkLogin();
    //查询Token信息
    public SaResult tokenInfo();
    //退出登录
    public SaResult logout();

}
