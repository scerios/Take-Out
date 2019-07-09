package practice.takeout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.takeout.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
