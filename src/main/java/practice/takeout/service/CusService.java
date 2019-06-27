package practice.takeout.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;
import practice.takeout.model.ErrorMsg;
import javax.servlet.http.HttpSession;

public interface CusService {
  void addCus(Cus cus);
  Cus getCusById(long id);
  void addDetailsToCus(long id, CusDetails cusDetails);
  String[] getDataFromDbByQuery(String query);
  boolean isCusHasAccess(HttpSession session);
  void accessDenied(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);
  long getCusSessionId(HttpSession session);
}
