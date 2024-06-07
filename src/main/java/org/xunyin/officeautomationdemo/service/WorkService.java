package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.pojo.Group;
import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.Work;

import java.util.List;

public interface WorkService {

    void createGroup(Group group);
    Result inviteMember(List<String> userNames);
    void publishWork(Work work);
    Work workInfo();
    void updateProgress(String progress);
    Result endWork();
}
