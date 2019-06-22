package practice.takeout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.takeout.model.Cus;

@Repository
public interface CusRepository extends CrudRepository<Cus, Long> {
}
