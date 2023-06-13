package lk.ijse.cooperative.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Interest {
    private Double loanInt;
    private Double depositInt;
    private Double serviceInt;
}
