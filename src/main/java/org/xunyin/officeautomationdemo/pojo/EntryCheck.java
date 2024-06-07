package org.xunyin.officeautomationdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryCheck {
    private int id;
    private int entryId;
    private String checkPerson;
    private int status;
    private LocalDateTime time;
    private int result;
    private String description;
}
