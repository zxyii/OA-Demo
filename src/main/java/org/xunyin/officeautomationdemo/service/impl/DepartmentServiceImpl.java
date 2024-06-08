package org.xunyin.officeautomationdemo.service.impl;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xunyin.officeautomationdemo.mapper.DepartmentMapper;
import org.xunyin.officeautomationdemo.pojo.Department;
import org.xunyin.officeautomationdemo.pojo.User;
import org.xunyin.officeautomationdemo.service.DepartmentService;
import org.xunyin.officeautomationdemo.service.UserService;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    UserService userService;
    @Override
    public List<String>
    searchMember() {
        User leader = userService.findById(StpUtil.getLoginIdAsInt());
        int departmentId = leader.getDepartment();
        return departmentMapper.searchMember(departmentId);
    }

    @Override
    public String searchLeader(int departmentId) {
        return departmentMapper.showLeader(departmentId);
    }

    /*
    成功加入部门
    */
    @Override
    public void entry(int departmentId) {
        int userId = StpUtil.getLoginIdAsInt();
        departmentMapper.entry(userId,departmentId);
    }


    @Override
    public void add(Department department) {
       departmentMapper.add(department);
    }

    @Override
    public void update(int departmentId) {
        int userId = StpUtil.getLoginIdAsInt();
        departmentMapper.update(userId,departmentId);
    }
}
