package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.Meal;
import practice.takeout.repository.MealRepository;

@Service
public class MealServiceImpl implements MealService {
  private MealRepository repository;

  public MealServiceImpl(MealRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addMeal(Meal meal) {
    this.repository.save(meal);
  }

  @Override
  public Meal getMealById(long id) {
    return this.repository.findById(id).get();
  }

  @Override
  public Meal saveMeal(Meal meal) {
    this.repository.save(meal);
    return meal;
  }
}
