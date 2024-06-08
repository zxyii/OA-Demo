package org.xunyin.officeautomationdemo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xunyin.officeautomationdemo.mapper.WorkMapper;
import org.xunyin.officeautomationdemo.pojo.Group;
import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.User;
import org.xunyin.officeautomationdemo.pojo.Work;
import org.xunyin.officeautomationdemo.service.GroupService;
import org.xunyin.officeautomationdemo.service.UserService;
import org.xunyin.officeautomationdemo.service.WorkService;

import java.time.LocalDateTime;
import java.util.List;

@Service
    public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkMapper workMapper;
    @Autowired
    GroupService groupService;
    @Autowired
    UserService userService;

    /*
    新建工作小组
    */
    @Override
    @Transactional
    public void createGroup(Group group) {
        groupService.add(group);
    }

    /*
    邀请小组成员
    */
    @Override
    public Result inviteMember(int groupId,List<String> userNames) {
        User leader = userService.findById(StpUtil.getLoginIdAsInt());
        groupService.update(leader.getUserId(),groupId);
        for (String userName : userNames) {
            User leader1 = userService.findById(StpUtil.getLoginIdAsInt());
            User user = userService.findByName(userName);
            if (user.getDepartment() == leader.getDepartment()){
                groupService.inviteMember(leader1.getGroup(),userName);
                return Result.success(userName + "已成功加入小组");
            }
        }
        return Result.error("只能邀请当前部门成员加入小组");
    }


    /*
    发布工作内容
    */
    @Override
    public void publishWork(Work work) {
        User leader = userService.findById(StpUtil.getLoginIdAsInt());
        work.setCreateTime(LocalDateTime.now());
        work.setUpdateTime(LocalDateTime.now());
        work.setGroupId(leader.getGroup());
        work.setCreatePerson(leader.getUserName());
        workMapper.publishWork(work);
    }

    /*
    查询工作信息
    */
    @Override
    public Work workInfo() {
        User user = userService.findById(StpUtil.getLoginIdAsInt());
        int groupId = user.getGroup();
        return workMapper.workInfo(groupId);
    }

    /*
    更新工作进度
    */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void updateProgress(String progress) {
        User user = userService.findById(StpUtil.getLoginIdAsInt());
        Work work = workMapper.workInfo(user.getGroup());
        work.setUpdateTime(LocalDateTime.now());
        if(work.getStatus() == 0) {
            workMapper.updateProgress(user.getGroup(), progress, work.getUpdateTime());
        }
        else if (work.getStatus() == 1){
            throw new RuntimeException("不可更新已完成任务");
        }
    }

    /*
    结束工作
    */
    @Override
    public Result endWork() {
        User user = userService.findById(StpUtil.getLoginIdAsInt());
        Work work = workMapper.workInfo(user.getGroup());
        work.setStatus(1);
        workMapper.updateStatus(work.getWorkId(),work.getStatus());
        return Result.success(work.getWorkName() + "已终止");
    }
}
