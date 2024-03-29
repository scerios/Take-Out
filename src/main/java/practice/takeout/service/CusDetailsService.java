package practice.takeout.service;

import practice.takeout.model.CusDetails;
import practice.takeout.model.Order;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CusDetailsService {
  void addDetails(CusDetails cusDetails);

  void delDetailsById(long id);

  CusDetails getDetailsById(long id);

  Iterable<CusDetails> findAllByCus_Id(long id);

  boolean isCusHasAccessToDetails(long cusId, long cusDetailsId);

  CusDetails giveTempCusDetailsValues(HttpSession session);

  List<String> getListOfNicknamesFromDbByQuery(long cusId);

  long getDetailsIdByCusIdAndNickname(long cusId, String nickname);

  void giveSessionTempDetails(CusDetails cusDetails, HttpSession session);

  void addOrderToDetails(long id, Order order);
}
