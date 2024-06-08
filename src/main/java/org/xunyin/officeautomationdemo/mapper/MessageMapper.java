package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xunyin.officeautomationdemo.pojo.Message;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Insert("insert into message(title,content,send_person,receive_department_id,create_date)" +
            "values (#{title},#{content},#{sendPerson},#{receiveDepartmentId},#{createDate})")
    void add(Message message);

    @Select(("select m.* from message m, department d where receive_department_id = #{departmentId} "))
    List<Message> list(int departmentId);
}
