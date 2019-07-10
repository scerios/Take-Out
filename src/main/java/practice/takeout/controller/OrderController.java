package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import practice.takeout.model.Burger;
import practice.takeout.service.BurgerServiceImpl;

@Controller
public class OrderController {
  private BurgerServiceImpl burgerService;

  @Autowired
  public OrderController(BurgerServiceImpl burgerService) {
    this.burgerService = burgerService;
  }

  @ModelAttribute
  public void getPage(Model model) {
    model.addAttribute("burger", new Burger());
  }

  @PostMapping("/order")
  public String addBurgerToCart(Burger burger) {
    long burgerId = burgerService.getBurgerIdByQuery(burger.getBun(), burger.getMeat(), burger.getCheese(), burger.getSauce());
    if (burgerId == 0) {
      burgerService.addBurger(burger);
    } else {
      System.out.println(burgerService.getBurgerById(burgerId).getBun());
    }
    return "redirect:/order";
  }
}
