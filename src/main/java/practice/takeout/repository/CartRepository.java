package practice.takeout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.takeout.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
