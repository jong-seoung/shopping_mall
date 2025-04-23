package shopping.shopping_mall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shopping.shopping_mall.model.Cart;
import shopping.shopping_mall.model.CartItem;
import shopping.shopping_mall.model.Item;
import shopping.shopping_mall.model.User;
import shopping.shopping_mall.repository.CartItemRepository;
import shopping.shopping_mall.repository.CartRepository;
import shopping.shopping_mall.repository.ItemRepository;
import shopping.shopping_mall.repository.UserRepositroy;

@Service
@RequiredArgsConstructor
public class CartService {
    
    private final UserRepositroy userRepositroy;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    // 장바구니 상품 목록 조회
    public List<CartItem> getCartItemsByUserId(Long userId) {
        Optional<User> user = userRepositroy.findById(userId);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user.get());
                    return cartRepository.save(newCart);
                });
        return cartItemRepository.findByCartId(cart.getId());
    }

    // 장바구니 상품 추가
    @Transactional
    public void addCartItem(Long userId, Long itemId, int amount) {
        Optional<User> user = userRepositroy.findById(userId);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user.get());
                    return cartRepository.save(newCart);
                });
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item not found"));

        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), itemId)
                .orElseGet(() -> {
                    CartItem newCartItem = new CartItem();
                    newCartItem.setCart(cart);
                    newCartItem.setItem(item);
                    newCartItem.setAmount(0);
                    return newCartItem;
                });
        cartItem.setAmount(cartItem.getAmount() + amount);
        cartItemRepository.save(cartItem);
    }
}
