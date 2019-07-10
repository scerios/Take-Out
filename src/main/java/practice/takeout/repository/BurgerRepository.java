package practice.takeout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.takeout.model.Burger;

@Repository
public interface BurgerRepository extends CrudRepository<Burger, Long> {
}
