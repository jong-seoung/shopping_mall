package shopping.shopping_mall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.shopping_mall.model.ItemImg;

public interface ItemImageRepository extends JpaRepository<ItemImg, Long> {
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    void deleteByItem_Id(Long itemId);
    List<ItemImg> findByItem_Id(Long itemId);
}
