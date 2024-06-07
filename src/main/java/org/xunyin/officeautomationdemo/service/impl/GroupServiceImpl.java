package org.xunyin.officeautomationdemo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.GroupMapper;
import org.xunyin.officeautomationdemo.pojo.Group;
import org.xunyin.officeautomationdemo.pojo.User;
import org.xunyin.officeautomationdemo.service.GroupService;
import org.xunyin.officeautomationdemo.service.UserService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Group group) {
        groupMapper.add(group);
    }

    @Override
    public void inviteMember(int groupId, String userName){
        User leader = userService.findById(StpUtil.getLoginIdAsInt());
        leader.setGroup(groupId);
        groupMapper.inviteMember(groupId,userName);
    }

    @Override
    public List<Group> listAll() {
        User user = userService.findById(StpUtil.getLoginIdAsInt());
        String groupLeader = user.getUserName();
        return groupMapper.listAll(groupLeader);
    }

}
