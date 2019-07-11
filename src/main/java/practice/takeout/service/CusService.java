package practice.takeout.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;
import practice.takeout.model.ErrorMsg;
import practice.takeout.model.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface CusService {
  void addCus(Cus cus);

  void updCusById(long id, Cus cus);

  Cus getCusById(long id);

  void addDetailsToCus(long id, CusDetails cusDetails);

  void giveCusSessionById(long id, HttpServletRequest request);

  void giveCusTempSessionToRegister(Cus cus, CusDetails cusDetails, HttpServletRequest request);

  Cus giveTempCusValues(HttpSession session);

  void endCusSession(HttpServletRequest request);

  String[] getDataFromDbByQuery(String email);

  void setAlreadyRegistered(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);

  void setWrongEmail(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);

  void setWrongPwd(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);

  boolean isCusHasAccess(HttpSession session);

  void accessDenied(ErrorMsg errorMsg, RedirectAttributes redirectAttributes);

  long getCusSessionId(HttpSession session);

  void addOrderToCus(long id, Order order);
}
