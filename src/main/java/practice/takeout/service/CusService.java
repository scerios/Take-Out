package practice.takeout.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.Cart;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;
import practice.takeout.model.ErrorMsg;
import javax.servlet.http.HttpSession;

public interface CusService {
  void addCus(Cus cus);

  void updCusById(long id, Cus cus);

  Cus getCusById(long id);

  void addDetailsToCus(long id, CusDetails cusDetails);

  void giveCusSessionById(long id, HttpSession session);

  void giveCusTempSessionToRegister(Cus cus, CusDetails cusDetails, HttpSession session);

  Cus giveTempCusValues(HttpSession session);

  void endCusSession(HttpSession session);

  String[] getDataFromDbByQuery(String email);

  void setAlreadyRegistered(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);

  void setWrongEmail(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);

  void setWrongPwd(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);

  boolean isCusHasAccess(HttpSession session);

  void accessDenied(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);

  long getCusSessionId(HttpSession session);

  void addCartToCus(long id, Cart cart);
}
