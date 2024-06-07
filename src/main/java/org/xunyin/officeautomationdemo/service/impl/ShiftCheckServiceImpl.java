package org.xunyin.officeautomationdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.ShiftCheckMapper;
import org.xunyin.officeautomationdemo.mapper.ShiftSubmitMapper;
import org.xunyin.officeautomationdemo.pojo.ShiftCheck;
import org.xunyin.officeautomationdemo.service.ShiftCheckService;
import org.xunyin.officeautomationdemo.service.ShiftSubmitService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftCheckServiceImpl implements ShiftCheckService {

    @Autowired
    ShiftCheckMapper shiftCheckMapper;

    /*
    通过转部门申请的编号查询对应审核信息
    */
    @Override
    public ShiftCheck check(int shiftId) {
        return shiftCheckMapper.check(shiftId);
    }

    /*
    更新审核信息
    */
    @Override
    public void updateResult(ShiftCheck shiftCheck) {
        shiftCheckMapper.updateResult(shiftCheck);
    }

    /*
    根据审核编号查询审核具体信息
    */
    @Override
    public ShiftCheck listInfo(int id) {
        return shiftCheckMapper.listInfo(id);
    }

    @Override
    public void initialize(int shiftId, String checkPerson) {
        LocalDateTime time = LocalDateTime.now();
        shiftCheckMapper.initialize(shiftId,checkPerson,time);
    }

}
