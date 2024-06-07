package org.xunyin.officeautomationdemo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.EntrySubmitMapper;
import org.xunyin.officeautomationdemo.pojo.EntrySubmit;
import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.User;
import org.xunyin.officeautomationdemo.service.EntrySubmitService;
import org.xunyin.officeautomationdemo.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntrySubmitServiceImpl implements EntrySubmitService {

    @Autowired
    EntrySubmitMapper entrySubmitMapper;
    @Autowired
    UserService userService;

    @Override
    public Result add(EntrySubmit entrySubmit) {
        LocalDateTime now = LocalDateTime.now();
        entrySubmit.setCreateTime(now);
        entrySubmit.setUserId(StpUtil.getLoginIdAsInt());
        if (entrySubmitMapper.listInfo2(entrySubmit.getUserId()) != null){
            return  Result.error("请勿重复提交");
        }
        entrySubmitMapper.add(entrySubmit);
        return Result.success("提交成功",entrySubmitMapper.listInfo2(entrySubmit.getUserId()));
    }

    /*
    查询申请加入当前部门的所有申请的具体信息
    */
    @Override
    public List<EntrySubmit> list() {
        int userId = StpUtil.getLoginIdAsInt();
        User user = userService.findById(userId);
        int departmentId = user.getDepartment();
        return entrySubmitMapper.listAll(departmentId);
    }

    /*
    通过申请编号查询申请相关信息
    */
    @Override
    public EntrySubmit listInfo1(int entryId) {
        return entrySubmitMapper.listInfo1(entryId);
    }

    /*
    通过申请人查询对应申请的相关信息
    */
    @Override
    public EntrySubmit listInfo2(int userId) {
        return entrySubmitMapper.listInfo2(userId);
    }


}
