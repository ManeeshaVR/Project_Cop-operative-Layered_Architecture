package lk.ijse.cooperative.entity.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PayLoanTM {
    private String loanId;
    private Double amount;
    private String payLoanId;
    private Double payAmount;
    private Double paidAmount;
    private Integer ins;
}
