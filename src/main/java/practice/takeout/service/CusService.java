package practice.takeout.service;

import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;

public interface CusService {
  void addCus(Cus cus);
  Cus getCusById(long id);
  void addDetailsToCus(long id, CusDetails cusDetails);
  String[] getDataFromDbByQuery(String query);
}
