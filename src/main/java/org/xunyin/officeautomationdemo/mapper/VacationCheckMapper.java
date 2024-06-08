package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xunyin.officeautomationdemo.pojo.VacationCheck;

import java.time.LocalDateTime;

@Mapper
public interface VacationCheckMapper {

    @Select("select * from vacation_check where vacation_id = #{vacationId}")
    VacationCheck check(int vacationId);
    @Update("update vacation_check set status =#{status},result = #{result},description = #{description} where id = #{id}")
    void updateResult(int id, int status, int result, String description);

    @Insert("insert into vacation_check(vacation_id,check_person,time) values (#{vacationId},#{checkPerson},#{time})")
    void initialize(int vacationId, String checkPerson, LocalDateTime time);
}
