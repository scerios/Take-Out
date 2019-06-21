package practice.takeout.service;

import practice.takeout.model.Meal;

public interface MealService {
  void addMeal(Meal meal);
  Meal getMealById(long id);
  Meal saveMeal(Meal meal);
}
