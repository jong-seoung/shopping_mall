package shopping.shopping_mall.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shopping.shopping_mall.constant.ItemSellStatus;
import shopping.shopping_mall.dto.ItemFormDto;
import shopping.shopping_mall.exception.OutOfStockException;

@Entity
@Table(name="item")
@Getter @Setter
@ToString
public class Item extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="item_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemName;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.itemDetail = itemFormDto.getItemDetail();
        this.price = itemFormDto.getPrice();
        this.stock = itemFormDto.getStock();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stock){
        int resetStock = this.stock - stock;
        if (resetStock < 0){
            throw new OutOfStockException("상품의 제고가 부족합니다. (남은 재고 수량: " + this.stock + ")");
        }
        this.stock = stock;
    }

    public void addStock(int stock){
        this.stock += stock;
    }
}
