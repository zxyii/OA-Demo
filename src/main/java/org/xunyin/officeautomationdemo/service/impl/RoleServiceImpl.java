package org.xunyin.officeautomationdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.RoleMapper;
import org.xunyin.officeautomationdemo.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;
    @Override
    public void updateRole(String userName, int roleId) {
        roleMapper.updateRole(userName,roleId);

    }
}
