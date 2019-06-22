package practice.takeout.service;

import practice.takeout.model.Cus;

public interface CusService {
  void addCus(Cus cus);
  Cus getCusById(long id);
}
