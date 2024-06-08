package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xunyin.officeautomationdemo.pojo.ShiftSubmit;

import java.util.List;

@Mapper
public interface ShiftSubmitMapper {

    @Insert("insert into shift_submit(apply_person,user_id,original_department_id,ideal_department_id,remark,create_time)" +
            "values (#{applyPerson},#{userId},#{originalDepartmentId},#{idealDepartmentId},#{remark},#{createTime})")
    void add(ShiftSubmit shiftSubmit);

    @Select("SELECT * from shift_submit where original_department_id or ideal_department_id = #{department}")
    List<ShiftSubmit> listAll(int departmentId);

    @Select(("select * from shift_submit where user_id = #{userId}"))
    ShiftSubmit listInfo(int userId);
}
