package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xunyin.officeautomationdemo.pojo.Group;

import java.util.List;

@Mapper
public interface GroupMapper {
    @Insert("insert into `group`(group_name,group_leader) values (#{groupName},#{groupLeader})")
    void add(Group group);

    @Update("update user set `group` = #{groupId} where user_name = #{userName}")
    void inviteMember(int groupId, String userName);

    @Select("select * from `group` where group_leader = #{groupLeader}")
    List<Group> listAll(String groupLeader);

    @Update("update user set `group` = #{groupId} where user_id = #{userId}")
    void update(int userId, int groupId);

    @Select("select user.user_name from user where `group` = #{group}")
    List<String> listMember(int group);
}
