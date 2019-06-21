package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dto.MealDetailsDto;
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

  @Override
  public MealDetailsDto MealToDto(Meal meal) {
    MealDetailsDto mealDetailsDto = new MealDetailsDto();
    mealDetailsDto.setId(meal.getId());
    mealDetailsDto.setName(meal.getName());
    mealDetailsDto.setBase(meal.getBase());
    mealDetailsDto.setTopping(meal.getTopping());
    mealDetailsDto.setStatus(meal.getStatus());
    return mealDetailsDto;
  }

  public Iterable<Meal> getAllMealsByStatus(String status) {
    return repository.findAllByStatus(status);
  }

  public Iterable<Meal> getAllMealsByTypeAndStatus(String type, String status) {
    return repository.findAllByTypeAndStatus(type, status);
  }
}
