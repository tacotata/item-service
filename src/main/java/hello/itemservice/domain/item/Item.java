package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data 위험해 DTO(일반적으로 데이터 왔다갔다 하는정도만 사용 )
//@Getter @Setter
@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    //int 사용하면 0이라도 들어가야함 가격이 0이면 모호함
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
