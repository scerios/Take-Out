package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.Address;
import practice.takeout.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
  private AddressRepository repository;

  AddressServiceImpl(AddressRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addAddress(Address address) {
    repository.save(address);
  }

  @Override
  public Address getAddressById(long id) {
    return repository.findById(id).get();
  }
}
