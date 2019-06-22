package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.CusDetails;
import practice.takeout.repository.CusDetailsRepository;

@Service
public class CusDetailsServiceImpl implements CusDetailsService {
  private CusDetailsRepository repository;

  CusDetailsServiceImpl(CusDetailsRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addDetails(CusDetails cusDetails) {
    repository.save(cusDetails);
  }

  @Override
  public CusDetails getDetailsById(long id) {
    return repository.findById(id).get();
  }
}
