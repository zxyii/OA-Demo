package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RoleMapper {

    @Update("update user set role = #{roleId} where user_name = #{userName}")
    void updateRole(String userName, int roleId);
}
