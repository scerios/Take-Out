package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.Address;
import practice.takeout.model.Cus;
import practice.takeout.model.Meal;
import practice.takeout.service.AddressServiceImpl;
import practice.takeout.service.CusServiceImpl;
import practice.takeout.service.OrderServiceImpl;

@Controller
public class IndexController {
  private OrderServiceImpl orderService;
  private CusServiceImpl cusService;
  private AddressServiceImpl addressService;

  @Autowired
  public IndexController(OrderServiceImpl orderService, CusServiceImpl cusService, AddressServiceImpl addressService) {
//    this.orderService = orderService;
    this.cusService = cusService;
    this.addressService = addressService;
  }

  @ModelAttribute
  public void getPage(Model model) {
    model.addAttribute("cus", new Cus());
    model.addAttribute("address", new Address());
  }

  @GetMapping("/")
  public String getIndexPage() {
    return "index";
  }

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @PostMapping("/register")
  public String sendRegister(Cus cus, Address address) {
    cusService.addCus(cus);
    addressService.addAddress(address);
    return "redirect:/";
  }

//  @GetMapping("/take order")
//  public String getTakeOrderPage() {
//    return "take order";
//  }

//  @PostMapping("/order")
//  public String sendOrder(Meal meal, RedirectAttributes redirectAttributes) {
//    if (meal.getTopping().equals("Smoked tofu")) {
//      meal.setType("vegetarian");
//    } else {
//      meal.setType("all");
//    }
//    meal.setStatus("ordered");
//    orderService.addMeal(meal);
//    redirectAttributes.addAttribute("id", meal.getId());
//    return "redirect:/order/{id}";
//  }

//  @GetMapping("/order/{id}")
//  public String getOrderPage(@PathVariable long id, Model model) {
//    if (orderService.getMealById(id) == null) {
//      return "error";
//    } else {
//      model.addAttribute("meal", orderService.getMealById(id));
//      return "order summary";
//    }
//  }
}
