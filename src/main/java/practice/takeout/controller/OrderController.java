package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.Meal;
import practice.takeout.service.MealServiceImpl;

@Controller
public class OrderController {
  private MealServiceImpl mealService;

  @Autowired
  public OrderController(MealServiceImpl mealService) {
    this.mealService = mealService;
  }

  @ModelAttribute
  public void getPages(Model model) {
    model.addAttribute("meal", new Meal());
  }

  @GetMapping("/")
  public String getIndex() {
    return "index";
  }

  @PostMapping("/order")
  public String sendOrder(Meal meal, RedirectAttributes redirectAttributes) {
    mealService.addMeal(meal);
    redirectAttributes.addAttribute("id", meal.getId());
    if (meal.getTopping().equals("Smoked Tofu")) {
      meal.setType("vegetarian");
    } else {
      meal.setType("all");
    }
    return "redirect:/order/{id}";
  }
}
