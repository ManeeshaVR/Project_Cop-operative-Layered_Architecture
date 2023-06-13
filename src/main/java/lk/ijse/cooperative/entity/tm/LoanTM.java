package lk.ijse.cooperative.entity.tm;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class LoanTM {
    private Integer memberNo;
    private String loanId;
    private Double amount;
    private Integer installments;
    private Double firIns;
    private Double insAmount;
    private Boolean completed;
    private Date date;

}
