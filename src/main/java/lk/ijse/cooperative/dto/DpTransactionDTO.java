package lk.ijse.cooperative.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DpTransactionDTO {
    private String transId;
    private String type;
    private Double amount;
    private Date date;
    private String desc;
    private String dpId;
}
