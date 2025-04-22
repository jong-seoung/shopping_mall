package shopping.shopping_mall.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shopping.shopping_mall.constant.ItemSellStatus;
import shopping.shopping_mall.model.Item;
import shopping.shopping_mall.model.ItemImg;

@Getter @Setter
public class ItemDetailDto {
    private Long id;
    private String itemName;
    private String itemDetail;
    private int price;
    private Integer stock;
    private ItemSellStatus itemSellStatus;
    private String repImgUrl;
    private List<ItemImg> itemImgList;

    public ItemDetailDto(Item item){
        this.id = item.getId();
        this.itemName = item.getItemName();
        this.itemDetail = item.getItemDetail();
        this.price = item.getPrice();
        this.stock = item.getStock();
        this.itemSellStatus = item.getItemSellStatus();
        this.itemImgList = item.getItemImgList();

        if (itemImgList != null) {
            this.repImgUrl = itemImgList.stream()
                .filter(img -> "Y".equals(img.getRepimgYn()))
                .map(ItemImg::getImgUrl)
                .findFirst()
                .orElse(null);
        } else {
            this.repImgUrl = "/images/default.png";
        }
    }
}
