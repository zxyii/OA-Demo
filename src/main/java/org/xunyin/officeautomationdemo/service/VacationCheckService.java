package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.pojo.VacationCheck;

import java.time.LocalDateTime;

public interface VacationCheckService {
    VacationCheck check(int vacationId);
    void updateResult(int id,int status,int result,String description);
    void initialize(int vacationId, String checkPerson);
}
