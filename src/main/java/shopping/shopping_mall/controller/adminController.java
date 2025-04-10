package shopping.shopping_mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shopping.shopping_mall.dto.ItemFormDto;
import shopping.shopping_mall.model.Item;
import shopping.shopping_mall.service.ItemService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class adminController {

    private final ItemService itemService;

    /** 어드민 상품 목폭 **/
    @GetMapping("/item/list")
    public String list(Model model){
        model.addAttribute("items", itemService.getItemList());
        return "admin/item/list";
    }

    /** 어드민 상품 생성 **/
    @GetMapping("/item/new")
    public String form(Model model){
        return "admin/item/form";
    }

    @PostMapping("/item/new")
    public String save(@ModelAttribute Item item){
        itemService.save(item);
        System.out.println("item:"+ item);
        return "redirect:/admin/item/list";
    }

    /** 어드민 상품 제거 **/
    @PostMapping("/item/delete/{id}")
    public String delete(@PathVariable Long id) {
        itemService.delete(id);
        return "redirect:/admin/item/list";
    }

    /** 어드민 상품 수정 **/
    @GetMapping("/item/edit/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        model.addAttribute("item", itemService.getItem(id));
        return "admin/item/edit";
    }

    @PostMapping("/item/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute ItemFormDto itemFormDto){
        itemService.updateItem(id, itemFormDto);
        return "redirect:/admin/item/list";
    }

}
