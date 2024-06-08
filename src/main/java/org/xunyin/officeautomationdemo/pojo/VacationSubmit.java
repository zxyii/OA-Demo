package org.xunyin.officeautomationdemo.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VacationSubmit {
    private int id;
    private String content;
    private String applyPerson;
    private int userId;
    private int departmentId;
    private String start;
    private String end;
    private LocalDateTime createTime;
}
