package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.VacationSubmit;

import java.util.List;

public interface VacationSubmitService {
    Result add(VacationSubmit vacationSubmit);

    VacationSubmit findById(int id);

    VacationSubmit listInfo(int userId);
    List<VacationSubmit> listAll();

    Result updateReason(String content);
}
