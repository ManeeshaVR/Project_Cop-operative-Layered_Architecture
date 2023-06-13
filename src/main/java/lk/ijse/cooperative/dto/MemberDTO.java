package lk.ijse.cooperative.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {

    private String nic;
    private String name;
    private Integer age;
    private String position;
    private String department;
    private Double salary;
    private Date joinDate;
}
