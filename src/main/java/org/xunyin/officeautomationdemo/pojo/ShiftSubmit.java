package org.xunyin.officeautomationdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftSubmit {
    private int id;
    private String applyPerson;
    private int userId;
    private int originalDepartmentId;
    private int idealDepartmentId;
    private String remark;
    private LocalDateTime createTime;
}
