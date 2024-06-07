package org.xunyin.officeautomationdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftCheck {
    private int id;
    private int shiftId;
    private String checkPerson;
    private int order;
    private int status;
    private int pass;
    private String description;
    private int result;
    private LocalDateTime time;
}
