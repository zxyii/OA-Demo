package org.xunyin.officeautomationdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int messageId;
    private String title;
    private String content;
    private String sendPerson;
    private int receiveDepartmentId;
    private LocalDateTime createDate;
}
