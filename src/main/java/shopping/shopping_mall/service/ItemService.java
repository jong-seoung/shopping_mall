package shopping.shopping_mall.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping.shopping_mall.dto.ItemFormDto;
import shopping.shopping_mall.model.Item;
import shopping.shopping_mall.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService{

    private final ItemRepository itemRepository;

    public ItemFormDto getItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이템 없음"));

        ItemFormDto dto = new ItemFormDto();
        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setItemDetail(item.getItemDetail());
        dto.setPrice(item.getPrice());
        dto.setStock(item.getStock());
        dto.setItemSellStatus(item.getItemSellStatus());
        return dto;
    }

    public void save(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long id, ItemFormDto itemFormDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이템 없음"));

        item.setItemName(itemFormDto.getItemName());
        item.setItemDetail(itemFormDto.getItemDetail());
        item.setPrice(itemFormDto.getPrice());
        item.setStock(itemFormDto.getStock());
        item.setItemSellStatus(itemFormDto.getItemSellStatus());
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }

    public List<Item> getItemList() {
        return itemRepository.findAll();
    }
}
