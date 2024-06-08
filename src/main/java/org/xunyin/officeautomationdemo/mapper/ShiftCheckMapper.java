package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xunyin.officeautomationdemo.pojo.ShiftCheck;

import java.time.LocalDateTime;

@Mapper
public interface ShiftCheckMapper {

    @Select(("select * from shift_check where shift_id = #{shiftId}"))
    ShiftCheck check(int shiftId);

    @Update(("update shift_check set`order` = #{order},pass = #{pass},description = #{description} where id = #{id}"))
    void update(int id, int order, int pass,String description);

    @Select("select * from shift_check where id = #{id}")
    ShiftCheck listInfo(int id);

    @Insert("insert into shift_check(shift_id,check_person,time) values (#{shiftId},#{checkPerson},#{time})")
    void initialize(int shiftId, String checkPerson, LocalDateTime time);

    @Update("update shift_check set status = #{status}, result = #{result} where id = #{id}")
    void updateResult(int id,int status, int result);
}
