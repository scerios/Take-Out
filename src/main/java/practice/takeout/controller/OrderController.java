package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
}
