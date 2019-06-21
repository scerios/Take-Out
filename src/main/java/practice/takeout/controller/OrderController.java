package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import practice.takeout.service.MealServiceImpl;

@Controller
public class OrderController {
  private MealServiceImpl mealService;

  @Autowired
  public OrderController(MealServiceImpl mealService) {
    this.mealService = mealService;
  }
}
