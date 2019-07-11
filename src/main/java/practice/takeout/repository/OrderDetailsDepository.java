package practice.takeout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.takeout.model.OrderDetails;

@Repository
public interface OrderDetailsDepository extends CrudRepository<OrderDetails, Long> {
}
