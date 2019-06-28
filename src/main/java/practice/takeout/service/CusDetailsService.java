package practice.takeout.service;

import practice.takeout.model.CusDetails;

public interface CusDetailsService {
  void addDetails(CusDetails cusDetails);
  CusDetails getDetailsById(long id);
  Iterable<CusDetails> findAllByCus_Id(long id);
}
