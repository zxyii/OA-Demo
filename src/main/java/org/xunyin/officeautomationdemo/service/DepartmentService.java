package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.pojo.Department;
import org.xunyin.officeautomationdemo.pojo.User;

import java.util.List;

public interface DepartmentService {

    List<String> searchMember();

    String searchLeader(int departmentId);

    void entry(int departmentId);

    void add(Department department);
}
