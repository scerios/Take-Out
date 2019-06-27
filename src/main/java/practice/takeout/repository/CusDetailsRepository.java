package practice.takeout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.takeout.model.CusDetails;

@Repository
public interface CusDetailsRepository extends CrudRepository<CusDetails, Long> {
  Iterable<CusDetails> findAllById(long id);
}
