package lk.ijse.cooperative.entity.tm;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OtherSerTM {
    private String serviceId;
    private String type;
    private Double amount;
    private Double interest;
    private Date date;
    private Integer memberNo;
    private Boolean completed;
}
