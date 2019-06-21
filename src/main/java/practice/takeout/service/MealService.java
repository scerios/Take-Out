package practice.takeout.service;

import practice.takeout.dto.MealDetailsDto;
import practice.takeout.model.Meal;

public interface MealService {
  void addMeal(Meal meal);
  Meal getMealById(long id);
  Meal saveMeal(Meal meal);
  MealDetailsDto MealToDto(Meal meal);
  Iterable<Meal> getAllMealsByStatus(String status);
  Iterable<Meal> getAllMealsByTypeAndStatus(String type, String status);
}
