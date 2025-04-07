package shopping.shopping_mall.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import shopping.shopping_mall.constant.ItemSellStatus;
import shopping.shopping_mall.model.Item;


@Getter @Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stock;

    private ItemSellStatus itemSellStauts;

    // 상품 수정 시 사용되는 멤버 변수들

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO -> Entity
    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }
}
