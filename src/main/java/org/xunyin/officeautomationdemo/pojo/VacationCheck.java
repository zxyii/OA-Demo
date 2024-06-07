package org.xunyin.officeautomationdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationCheck {
    private int id;
    private int vacationId;
    private String checkPerson;
    private int status;
    private int result;
    private String description;
    private LocalDateTime time;
}
