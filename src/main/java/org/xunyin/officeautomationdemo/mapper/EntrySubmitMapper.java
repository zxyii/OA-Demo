package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xunyin.officeautomationdemo.pojo.EntrySubmit;

import java.util.List;

@Mapper
public interface EntrySubmitMapper {

    @Insert("insert into entry_submit(apply_person,user_id,ideal_department_id,remark,create_time)" +
            "values (#{applyPerson},#{userId},#{idealDepartmentId},#{remark},#{createTime})")
    void add(EntrySubmit entrySubmit);

    @Select("select * from entry_submit where ideal_department_id = #{departmenrId}")
    List<EntrySubmit> listAll(int departmentId);

    @Select("select * from entry_submit where id = #{entryId}")
    EntrySubmit listInfo1(int entryId);

    @Select("select * from entry_submit where user_id = #{UserId}")
    EntrySubmit listInfo2(int userID);
}
