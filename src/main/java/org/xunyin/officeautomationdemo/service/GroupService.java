package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.pojo.Group;

import java.util.List;

public interface GroupService {
    void add(Group group);
    void inviteMember(int groupId,String userNames);

    List<Group> listAll();
}
