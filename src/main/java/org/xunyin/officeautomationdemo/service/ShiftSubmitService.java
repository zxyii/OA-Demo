package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.ShiftSubmit;

import java.util.List;

public interface ShiftSubmitService {

    Result add(ShiftSubmit shiftSubmit);
    List<ShiftSubmit> listAll();

    ShiftSubmit listInfo(int userId);


}
