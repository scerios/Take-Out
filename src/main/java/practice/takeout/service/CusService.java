package practice.takeout.service;

import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;

public interface CusService {
  void addCus(Cus cus);
  Cus getCusById(long id);
  void addDetailsToCus(long id, CusDetails cusDetails);
  String getDataFromDbByQuery(String query);
  String generatePinForReference();
  void setTempCusPin(long id, String pin);
  String getTempCusPin(long id);
  void delTempCusPin(long id);
}
