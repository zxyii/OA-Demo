package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xunyin.officeautomationdemo.pojo.VacationSubmit;

import java.util.List;

@Mapper
public interface VacationSubmitMapper {

    @Insert("insert into vacation_submit(apply_person,user_id,department_id,content,start,end,create_time)" +
            "values (#{applyPerson},#{userId},#{departmentId},#{content},#{start},#{end},#{createTime})")
    void add(VacationSubmit vacationSubmit);

    @Select("select * from vacation_submit where id = #{id}")
    VacationSubmit findById(int id);

    @Select("select * from vacation_submit where department_id = #{departmentId}")
    List<VacationSubmit> listAll(int departmentId);
    @Update(("update vacation_submit  set content = #{content} where apply_person = #{applyPerson} "))
    void updateReason(String applyPerson,String content);

    @Select("select * from vacation_submit where user_id = #{userId}")
    VacationSubmit listInfo(int userId);
}
