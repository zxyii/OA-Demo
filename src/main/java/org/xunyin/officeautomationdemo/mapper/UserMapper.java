package org.xunyin.officeautomationdemo.mapper;

import org.apache.ibatis.annotations.*;
import org.xunyin.officeautomationdemo.pojo.Department;
import org.xunyin.officeautomationdemo.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from  user where user_id = #{userId}")
    User findById(int userId);

    @Select("select * from user where user_name = #{userName}")
    User findByName(String userName);

    @Select("select * from user")
    List<User> listAll();

    @Insert("insert into user(user_name,password,salt,gender,card_id,phone)" +
            "values(#{userName},#{password},#{salt},#{gender},#{cardId},#{phone})")
    void add(User user);

    @Update("update user set password = #{password} where user_id = #{userId}")
    void updatePwd(int userId, String password);

    @Delete("delete from user where user_id = #{userId}")
    void deleteById(int userId);

    @Delete("delete from user where user_name = #{userName}")
    void deleteByName(String userName);

    @Select("select department_name from department where department_id= #{departmentID}")
    String searchDpt(int departmentId);

    @Update("update user set image = #{imageUrl} where user_id = #{userId} ")
    void updateImage(int userId,String imageUrl);

    @Select("select r.role_name from role r inner join user u on u.role = r.role_id where u.user_id = #{userId}")
    String getRole(int userId);
}
