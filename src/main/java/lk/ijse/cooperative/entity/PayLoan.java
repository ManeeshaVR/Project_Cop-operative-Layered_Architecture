package lk.ijse.cooperative.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PayLoan {
    private String dpLId;
    private Double loanAmount;
    private Double payAmount;
    private Double paidAmount;
    private int compInstallments;
    private String lId;
}
