package practice.takeout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.takeout.model.Cart;
import javax.transaction.Transactional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
  @Transactional
  void deleteAllByCus_Id(long id);
}
