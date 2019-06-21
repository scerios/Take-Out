package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.repository.MealRepository;

@Service
public class MealServiceImpl implements MealService {
  private MealRepository repository;

  public MealServiceImpl(MealRepository repository) {
    this.repository = repository;
  }
}
