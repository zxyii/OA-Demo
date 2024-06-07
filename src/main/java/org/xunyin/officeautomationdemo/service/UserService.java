package org.xunyin.officeautomationdemo.service;

import org.xunyin.officeautomationdemo.util.Result;
import org.xunyin.officeautomationdemo.pojo.User;

import java.util.List;

public interface UserService {
    User findById(int userId);
    User findByName(String userName);
    List<User> listAll();
    Result register(User user);
    Result searchDpt();
    User userInfo();
    Result updatePwd(String password);

    String getRole(int userId);
    void deleteUserByName(String userName);

    Result updateImage(String imageUrl);
}
