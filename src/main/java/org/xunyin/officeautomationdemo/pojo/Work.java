package org.xunyin.officeautomationdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    private int workId;
    private String workName;
    private String content;
    private String createPerson;
    private int groupId;
    private String progress;
    private int status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
