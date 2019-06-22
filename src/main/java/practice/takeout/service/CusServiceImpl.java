package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.CusDetails;
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
  public void addDetailsToCus(long id, CusDetails cusDetails) {
    repository.findById(id).get().addCusDetails(cusDetails);
  }
}
