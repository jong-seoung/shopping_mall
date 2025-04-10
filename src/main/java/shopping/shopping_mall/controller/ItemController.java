package shopping.shopping_mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shopping.shopping_mall.dto.ItemFormDto;
import shopping.shopping_mall.service.ItemService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", itemService.getItemList());
        return "items/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.getItem(id));
        return "items/form";
    }
}
