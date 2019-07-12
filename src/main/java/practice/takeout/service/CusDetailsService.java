package practice.takeout.service;

import practice.takeout.model.CusDetails;
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
}
