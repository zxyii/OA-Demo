package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.pojo.ShiftCheck;
import org.xunyin.officeautomationdemo.pojo.ShiftSubmit;

import java.time.LocalDateTime;

public interface ShiftCheckService {

    ShiftCheck check(int shiftId);
    void updateResult(ShiftCheck shiftCheck);
    ShiftCheck listInfo(int id);

    void initialize(int shiftId,String applyPerson);
}
