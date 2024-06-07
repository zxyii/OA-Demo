package org.xunyin.officeautomationdemo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.ShiftSubmitMapper;
import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.ShiftSubmit;
import org.xunyin.officeautomationdemo.pojo.User;
import org.xunyin.officeautomationdemo.service.DepartmentService;
import org.xunyin.officeautomationdemo.service.ShiftCheckService;
import org.xunyin.officeautomationdemo.service.ShiftSubmitService;
import org.xunyin.officeautomationdemo.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShiftSubmitServiceImpl implements ShiftSubmitService {

    @Autowired
    ShiftSubmitMapper shiftSubmitMapper;
    @Autowired
    UserService userService;
    @Autowired
    ShiftCheckService shiftCheckService;
    @Autowired
    DepartmentService departmentService;

    /*
    转部门申请提交
    */
    @Override
    public Result add(ShiftSubmit shiftSubmit) {
        LocalDateTime now = LocalDateTime.now();
        shiftSubmit.setCreateTime(now);
        shiftSubmit.setUserId(StpUtil.getLoginIdAsInt());
        if (shiftSubmitMapper.listInfo(StpUtil.getLoginIdAsInt()) != null){
            return  Result.error("请勿重复提交");
        }
        shiftSubmitMapper.add(shiftSubmit);
        return Result.success("提交成功",shiftSubmitMapper.listInfo(shiftSubmit.getUserId()));

    }

    /*
    查询所有转部门申请相关信息
    */
    @Override
    public List<ShiftSubmit> listAll() {
        int userId = StpUtil.getLoginIdAsInt();
        User user = userService.findById(userId);
        int departmentId = user.getDepartment();
        return shiftSubmitMapper.listAll(departmentId);
    }


    /*
   通过申请人查询对应转部门申请的相关信息
   */
    @Override
    public ShiftSubmit listInfo(int userId) {
        return shiftSubmitMapper.listInfo(userId);
    }
}
