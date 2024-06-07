package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xunyin.officeautomationdemo.pojo.Department;
import org.xunyin.officeautomationdemo.pojo.User;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Select("select user_name from user where department = #{departmentId}")
    List<String> searchMember(int departmentId);

    @Select("select u.user_name from department d inner join user u on d.department_id = u.department where u.department = #{departmentId}")
    String showLeader(int departmentId);

    @Update("update user set department = #{departmentId}")
    void entry(int departmentId);

    @Insert("insert into department(department_name, department_leader) " +
            "values (#{departmentName},#{departmentLeader})")
    void add(Department department);
}
