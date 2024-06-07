package org.xunyin.officeautomationdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private int userId;
    private String userName;
    private String password;
    private String salt;
    private char gender;
    private Integer cardId;
    private String phone;
    private String image;
    private int role;
    private  int department;
    private int group;
}
