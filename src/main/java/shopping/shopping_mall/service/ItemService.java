package shopping.shopping_mall.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import shopping.shopping_mall.dto.ItemFormDto;
import shopping.shopping_mall.model.Item;
import shopping.shopping_mall.model.ItemImg;
import shopping.shopping_mall.repository.ItemImageRepository;
import shopping.shopping_mall.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService{

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final FileService fileService;

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

    public void saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        Item item = new Item();
        item.setItemName(itemFormDto.getItemName());
        item.setItemDetail(itemFormDto.getItemDetail());
        item.setPrice(itemFormDto.getPrice());
        item.setStock(itemFormDto.getStock());
        item.setItemSellStatus(itemFormDto.getItemSellStatus());

        itemRepository.save(item);

        for(int i =0; i<itemImgFileList.size(); i++){
            MultipartFile file = itemImgFileList.get(i);
            String fileName = fileService.uploadFile(file.getOriginalFilename(), file.getBytes());

            ItemImg img = new ItemImg();
            img.setImgName(fileName);
            img.setRepimgYn(i == 0 ? "Y" : "N");
            img.setItem(item);

            itemImageRepository.save(img);
        }
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
