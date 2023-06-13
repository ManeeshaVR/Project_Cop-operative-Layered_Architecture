package lk.ijse.cooperative.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PayLoanDTO {
    private String dpLId;
    private Double loanAmount;
    private Double payAmount;
    private Double paidAmount;
    private int compInstallments;
    private String lId;
}
