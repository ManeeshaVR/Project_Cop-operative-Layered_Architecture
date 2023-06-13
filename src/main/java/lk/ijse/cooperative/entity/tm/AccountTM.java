package lk.ijse.cooperative.entity.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountTM {
    private String memberNo;
    private String name;
    private String nic;
    private Double shares;
    private Double comDeposits;
    private Double specDeposits;
    private Double penDeposits;
}
