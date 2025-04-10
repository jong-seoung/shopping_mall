package shopping.shopping_mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.shopping_mall.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
