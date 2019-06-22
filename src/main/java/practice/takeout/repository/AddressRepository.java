package practice.takeout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import practice.takeout.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
