package org.xunyin.officeautomationdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationSubmit {
    private int id;
    private String content;
    private String applyPerson;
    private int userId;
    private int departmentId;
    private Date start;
    private Date end;
    private LocalDateTime createTime;
}
