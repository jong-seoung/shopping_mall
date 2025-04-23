package shopping.shopping_mall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.shopping_mall.model.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}
