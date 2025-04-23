package shopping.shopping_mall.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import shopping.shopping_mall.config.auth.PrincipalDetails;
import shopping.shopping_mall.model.CartItem;
import shopping.shopping_mall.model.User;
import shopping.shopping_mall.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    public String listCart(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
        User user = principal.getUser();
        List<CartItem> cartItems = cartService.getCartItemsByUserId(user.getId());
        model.addAttribute("cartItems", cartItems);

        return "cart/list";
    }
    
    
    // 장바구니 상품 추가
    @PostMapping("/add")
    public String addCart(@RequestParam Long itemId, @RequestParam int amount, @AuthenticationPrincipal PrincipalDetails principal) {
        User user = principal.getUser();
        cartService.addCartItem(user.getId(), itemId, amount); 
        return "redirect:/cart";
    }
    // 장바구니 상품 삭제
    // 장바구니 상품 수량 변경
    // 장바구니 상품 목록 조회
    // 장바구니 비우기
}
