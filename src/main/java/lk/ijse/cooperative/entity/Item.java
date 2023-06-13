package lk.ijse.cooperative.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    private String itemId;
    private String name;
    private String type;
    private Double unitPrice;
    private String description;
    private Integer qty;
}
