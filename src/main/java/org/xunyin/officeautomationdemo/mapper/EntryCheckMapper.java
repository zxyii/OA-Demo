package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xunyin.officeautomationdemo.pojo.EntryCheck;

import java.time.LocalDateTime;

@Mapper
public interface EntryCheckMapper {
    @Update("update entry_check ec set result = #{result},status = #{status},description = #{description}  where ec.entry_id = #{id}")
    void updateResult(int id, int status, int result,String description);

    @Select("select * from entry_check where entry_id = #{entryId}")
    EntryCheck check(int entryId);

    @Select("select * from entry_check where id = #{id}")
    EntryCheck listInfo(int id);

    @Insert("insert into entry_check(entry_id,check_person,time)" +
            "values (#{entryId},#{checkPerson},#{time})")
    void initialize(int entryId, String checkPerson, LocalDateTime time);
}
