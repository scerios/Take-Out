package practice.takeout.service;

import practice.takeout.dto.OrderDetailsDto;
import practice.takeout.model.Meal;

public interface OrderService {
  void addMeal(Meal meal);
  Meal getMealById(long id);
  Meal saveMeal(Meal meal);
  OrderDetailsDto MealToDto(Meal meal);
  Iterable<Meal> getAllMealsByStatus(String status);
  Iterable<Meal> getAllMealsByTypeAndStatus(String type, String status);
}
