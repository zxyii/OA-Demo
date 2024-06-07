package org.xunyin.officeautomationdemo.config;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.xunyin.officeautomationdemo.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class AuthorizationConfiguration implements StpInterface {
    @Autowired
    UserService userService;
    @Override
    public List<String> getPermissionList(Object userId, String s) {
        return Collections.singletonList("*");
    }

    @Override
    public List<String> getRoleList(Object userId, String s) {
        List<String> roleList = new ArrayList<>();
        roleList.add(userService.getRole(Integer.parseInt((String) userId)));
        return roleList;
    }
}
