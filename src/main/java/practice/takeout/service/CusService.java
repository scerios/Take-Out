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
  byte getIsLoggedIn(long id);
  void setIsLoggedIn(long id, byte isLoggedIn);
  String[] getDataFromDbByQuery(String email);
  void setAlreadyRegistered(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);
  void setAlreadyLoggedIn(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);
  void setWrongEmail(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);
  void setWrongPwd(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);
  boolean isCusHasAccess(HttpSession session);
  void accessDenied(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);
  long getCusSessionId(HttpSession session);
}
