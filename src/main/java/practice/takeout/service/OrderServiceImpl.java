package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dto.OrderDetailsDto;
import practice.takeout.model.Meal;
import practice.takeout.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
  private OrderRepository repository;

  public OrderServiceImpl(OrderRepository repository) {
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
  public OrderDetailsDto MealToDto(Meal meal) {
    OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
    orderDetailsDto.setId(meal.getId());
    orderDetailsDto.setName(meal.getName());
    orderDetailsDto.setBase(meal.getBase());
    orderDetailsDto.setTopping(meal.getTopping());
    orderDetailsDto.setStatus(meal.getStatus());
    return orderDetailsDto;
  }

  public Iterable<Meal> getAllMealsByStatus(String status) {
    return repository.findAllByStatus(status);
  }

  public Iterable<Meal> getAllMealsByTypeAndStatus(String type, String status) {
    return repository.findAllByTypeAndStatus(type, status);
  }
}
