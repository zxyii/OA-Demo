package org.xunyin.officeautomationdemo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.VacationSubmitMapper;
import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.User;
import org.xunyin.officeautomationdemo.pojo.VacationSubmit;
import org.xunyin.officeautomationdemo.service.DepartmentService;
import org.xunyin.officeautomationdemo.service.UserService;
import org.xunyin.officeautomationdemo.service.VacationCheckService;
import org.xunyin.officeautomationdemo.service.VacationSubmitService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class VacationSubmitServiceImpl implements VacationSubmitService {

    @Autowired
    VacationSubmitMapper vacationSubmitMapper;
    @Autowired
    UserService userService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    VacationCheckService vacationCheckService;

    @Override
    public Result add(VacationSubmit vacationSubmit) {
        vacationSubmit.setCreateTime(LocalDateTime.now());
        vacationSubmit.setUserId(StpUtil.getLoginIdAsInt());
        vacationSubmit.setUserId(StpUtil.getLoginIdAsInt());
        if (vacationSubmitMapper.listInfo(vacationSubmit.getUserId()) != null){
            return  Result.error("请勿重复提交");
        }
        vacationSubmitMapper.add(vacationSubmit);
        return Result.success("提交成功",vacationSubmitMapper.listInfo(vacationSubmit.getUserId()));
    }

    @Override
    public VacationSubmit findById(int id) {
        return vacationSubmitMapper.findById(id);
    }

    @Override
    public VacationSubmit listInfo(int userId) {
        return vacationSubmitMapper.listInfo(userId);
    }

    @Override
    public List<VacationSubmit> listAll() {
        int userId = StpUtil.getLoginIdAsInt();
        User dptLeader = userService.findById(userId);
        return vacationSubmitMapper.listAll(dptLeader.getDepartment());
    }

    @Override
    public Result  updateReason(String content) {
        User user = userService.findById(StpUtil.getLoginIdAsInt());
        String applyPerson = user.getUserName();
        VacationSubmit vacationSubmit = vacationSubmitMapper.listInfo(user.getUserId());
        if(!Objects.equals(content, vacationSubmit.getContent())) {
            vacationSubmitMapper.updateReason(applyPerson, content);
            return Result.success("修改成功，请等候通知");
        }
        return Result.error("理由不能与原理由相同");
    }
}
