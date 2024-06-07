package org.xunyin.officeautomationdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.EntryCheckMapper;
import org.xunyin.officeautomationdemo.pojo.EntryCheck;
import org.xunyin.officeautomationdemo.service.EntryCheckService;

import java.time.LocalDateTime;

@Service
public class EntryCheckServiceImpl implements EntryCheckService {

    @Autowired
    EntryCheckMapper entryCheckMapper;

    /*
    通过申请的编号查询审核信息
    */
    @Override
    public EntryCheck check(int entryId) {
        return entryCheckMapper.check(entryId);
    }

    @Override
    public void updateResult(int id, int status, int result, String description) {
        entryCheckMapper.updateResult(id,status,result,description);
    }

    /*
    根据审核编号查询该审核具体信息
    */
    @Override
    public EntryCheck listInfo(int id) {
        return entryCheckMapper.listInfo(id);
    }

    /*
    初始化审核信息
    */
    @Override
    public void initialize(int entryId, String checkPerson) {
        LocalDateTime t = LocalDateTime.now();
        entryCheckMapper.initialize(entryId,checkPerson,t);
    }
}
