package org.xunyin.officeautomationdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.VacationCheckMapper;
import org.xunyin.officeautomationdemo.pojo.VacationCheck;
import org.xunyin.officeautomationdemo.service.VacationCheckService;

import java.time.LocalDateTime;

@Service
public class VacationCheckServiceImpl implements VacationCheckService {

    @Autowired
    VacationCheckMapper vacationCheckMapper;

    /*
    根据请假编号查询对应审核信息
    */
    @Override
    public VacationCheck check(int vacationId) {
        return vacationCheckMapper.check(vacationId);
    }

    /*
    更新审核信息
    */
    @Override
    public void updateResult(int id, int status, int result, String description) {
        vacationCheckMapper.updateResult(id,status,result,description);
    }

    /*
    初始化添加审核
    */
    @Override
    public void initialize(int vacationId, String checkPerson) {
        LocalDateTime time = LocalDateTime.now();
        vacationCheckMapper.initialize(vacationId,checkPerson,time);
    }
}
