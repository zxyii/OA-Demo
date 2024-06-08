package org.xunyin.officeautomationdemo.service;
import org.xunyin.officeautomationdemo.pojo.ShiftCheck;

public interface ShiftCheckService {

    ShiftCheck check(int shiftId);
    void update(int id, int order, int pass,String description);
    ShiftCheck listInfo(int id);

    void initialize(int shiftId,String applyPerson);

    void updateResult(int id,int status, int result);
}
