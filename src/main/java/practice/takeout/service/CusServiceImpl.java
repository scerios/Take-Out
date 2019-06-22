package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.Address;
import practice.takeout.model.Cus;
import practice.takeout.repository.CusRepository;

@Service
public class CusServiceImpl implements CusService {
  private CusRepository repository;

  CusServiceImpl(CusRepository repository) {
    this.repository = repository;
  }
  @Override
  public void addCus(Cus cus) {
    repository.save(cus);
  }

  @Override
  public Cus getCusById(long id) {
    return repository.findById(id).get();
  }

  @Override
  public void addAddressToCus(long id, Address address) {
    repository.findById(id).get().addCusAddress(address);
  }
}