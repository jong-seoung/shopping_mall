package shopping.shopping_mall.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import shopping.shopping_mall.dto.ItemDetailDto;
import shopping.shopping_mall.dto.ItemFormDto;
import shopping.shopping_mall.dto.ItemListDto;
import shopping.shopping_mall.model.Item;
import shopping.shopping_mall.model.ItemImg;
import shopping.shopping_mall.repository.ItemImageRepository;
import shopping.shopping_mall.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService{

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final FileService fileService;

    public ItemDetailDto getItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이템 없음"));

        ItemDetailDto itemDetailDto = new ItemDetailDto(item);
        return itemDetailDto;
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
            img.setImgUrl("/images/"+fileName);
            img.setRepimgYn(i == 0 ? "Y" : "N");
            img.setItem(item);

            itemImageRepository.save(img);
        }
    }

    @Transactional
    public void updateItem(Long id, ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이템 없음"));

        item.setItemName(itemFormDto.getItemName());
        item.setItemDetail(itemFormDto.getItemDetail());
        item.setPrice(itemFormDto.getPrice());
        item.setStock(itemFormDto.getStock());
        item.setItemSellStatus(itemFormDto.getItemSellStatus());

        itemRepository.save(item);

        List<ItemImg> existingImgs = itemImageRepository.findByItemIdOrderByIdAsc(id);

        for (ItemImg img : existingImgs) {
            if (!img.getImgName().equals("/images/default.jpg")) {
                fileService.deleteFile(img.getImgName());
                itemImageRepository.delete(img);           
            }
        }

        for(int i = 0; i < itemImgFileList.size(); i++) {
            MultipartFile file = itemImgFileList.get(i);
            String fileName = fileService.uploadFile(file.getOriginalFilename(), file.getBytes());

            ItemImg img = new ItemImg();
            img.setImgName(fileName);
            img.setImgUrl("/images/" + fileName);
            img.setRepimgYn(i == 0 ? "Y" : "N");
            img.setItem(item);
            itemImageRepository.save(img);
        }
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }

    public List<ItemListDto> getItemList() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemListDto::new)
                .collect(Collectors.toList());
    }
}
