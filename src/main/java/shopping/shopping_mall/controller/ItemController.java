package shopping.shopping_mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shopping.shopping_mall.model.Item;
import shopping.shopping_mall.service.ItemService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class MainController {

    private final ItemService itemService;

    // 상품 목록
    @GetMapping
    public String list(Model model){
        model.addAttribute("items", itemService.findAll());
        return "items/list";
    }

    // 상품 생성
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("item", new Item());
        return "items/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Item item) {
        itemService.save(item);
        return "redirect:/items";
    }
}
