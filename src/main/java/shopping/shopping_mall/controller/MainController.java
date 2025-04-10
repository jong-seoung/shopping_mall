package shopping.shopping_mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shopping.shopping_mall.service.ItemService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @GetMapping({"/",""})
    public String index(Model model){
        model.addAttribute("items", itemService.getItemList());
        return "index";
    }
}
