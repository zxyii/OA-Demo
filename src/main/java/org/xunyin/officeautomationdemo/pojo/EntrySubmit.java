package org.xunyin.officeautomationdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrySubmit {
    private int id;
    private String applyPerson;
    private int userId;
    private int idealDepartmentId;
    private String remark;
    private LocalDateTime createTime;
}
