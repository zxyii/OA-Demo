package org.xunyin.officeautomationdemo.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.xunyin.officeautomationdemo.mapper.UserMapper;
import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.User;
import org.xunyin.officeautomationdemo.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public User findById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public User findByName(String userName) {
        return userMapper.findByName(userName);
    }

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }

    @Override
    public Result register(@RequestBody User user) {
        if(userMapper.findByName(user.getUserName()) == null){
            String salt = UUID.randomUUID().toString().replace("-","");
            user.setSalt(salt);
            user.setPassword(SaSecureUtil.md5BySalt(user.getPassword(),salt));
            userMapper.add(user);
            return Result.success("注册成功");
        }else {
            return Result.success("用户已存在");
        }
    }


    @Override
    public Result searchDpt() {
        int userId = StpUtil.getLoginIdAsInt();
        User user = userMapper.findById(userId);
        if(userMapper.searchDpt(user.getDepartment()) == null){
            return Result.error("暂未加入部门");
        }
        return Result.success("所属部门：" ,userMapper.searchDpt(user.getDepartment()));
    }

    @Override
    public User userInfo() {
        int userId = StpUtil.getLoginIdAsInt();
        User user = userMapper.findById(userId);
        return user;
    }

    @Override
    public Result updatePwd(String newPassword) {
        User user = userMapper.findById(StpUtil.getLoginIdAsInt());
        String salt = user.getSalt();
        if(user.getPassword().equals(SaSecureUtil.md5BySalt(newPassword,salt))){
            return Result.error("密码不能与原密码相同");
        }else {
            String newSalt = UUID.randomUUID().toString().replace("-","");
            user.setPassword(SaSecureUtil.md5BySalt(newPassword,newSalt));
            userMapper.updatePwd(user.getUserId(),user.getPassword());
            return Result.success("密码更新成功");
        }
    }

    @Override
    public Result updateImage(String imageUrl) {
        User user = userMapper.findById(StpUtil.getLoginIdAsInt());
        userMapper.updateImage(user.getUserId(),imageUrl);
        return Result.success("更新成功",imageUrl);
    }

    @Override
    public String getRole(int userId) {
        return userMapper.getRole(userId);
    }


    @Override
    public void deleteUserByName(String userName) {
        userMapper.deleteByName(userName);
    }

}
