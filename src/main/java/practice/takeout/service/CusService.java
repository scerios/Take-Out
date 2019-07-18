package practice.takeout.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.Cart;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;
import practice.takeout.model.PopUpMsq;
import javax.servlet.http.HttpSession;
import java.util.List;

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

  void setAlreadyRegistered(PopUpMsq popUpMsq, RedirectAttributes redirectAttributes);

  void setWrongEmail(PopUpMsq popUpMsq, RedirectAttributes redirectAttributes);

  void setWrongPwd(PopUpMsq popUpMsq, RedirectAttributes redirectAttributes);

  boolean isCusHasAccess(HttpSession session);

  void accessDenied(PopUpMsq popUpMsq, RedirectAttributes redirectAttributes);

  long getCusSessionId(HttpSession session);

  void addCartToCus(long id, Cart cart);

  List<CusDetails> getCusDetailsListBySession(HttpSession session);
}
