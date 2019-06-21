package practice.takeout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.takeout.model.Meal;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {
  Iterable<Meal> findAllByStatus(String status);
  Iterable<Meal> findAllByTypeAndStatus(String type, String status);
}
