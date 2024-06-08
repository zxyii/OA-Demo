package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xunyin.officeautomationdemo.pojo.Work;

import java.time.LocalDateTime;

@Mapper
public interface WorkMapper {
    @Insert("insert into work(work_name,content,create_person,group_id,create_time,update_time)" +
            "values (#{workName},#{content},#{createPerson},#{groupId},#{createTime},#{updateTime})")
    void publishWork(Work work);

    @Update(("update work set progress = #{progress},update_time = #{updateTime} where group_id = #{groupId}"))
    void updateProgress(int groupId, String progress,LocalDateTime updateTime);

    @Select("select * from work where group_id = #{groupId}")
    Work workInfo(int groupId);

    @Update("update work set status = #{status} where work_id = #{workId}")
    void updateStatus(int workId, int status);
}
