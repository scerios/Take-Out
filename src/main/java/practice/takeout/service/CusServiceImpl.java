package practice.takeout.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;
import practice.takeout.model.ErrorMsg;
import practice.takeout.repository.CusRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;

@Service
public class CusServiceImpl implements CusService {
  private SystemDefaults defaults = new SystemDefaults();
  private CusRepository repository;
  private final String JDBC_DRIVER = defaults.getJdbcDriver();
  private final String DB_URL = defaults.getDbUrl();
  private final String USERNAME = defaults.getUserName();
  private final String PWD = defaults.getPwd();

  CusServiceImpl(CusRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addCus(Cus cus) {
    repository.save(cus);
  }

  @Override
  public void updCusById(long id, Cus cus) {
    Cus cusToUpd = getCusById(id);
    cusToUpd.setFirstName(cus.getFirstName());
    cusToUpd.setLastName(cus.getLastName());
    cusToUpd.setPhoneNumber(cus.getPhoneNumber());
    repository.save(cusToUpd);
  }

  @Override
  public Cus getCusById(long id) {
    return repository.findById(id).get();
  }

  @Override
  public void addDetailsToCus(long id, CusDetails cusDetails) {
    repository.findById(id).get().addCusDetails(cusDetails);
  }

  @Override
  public void giveCusSessionById(long id, HttpServletRequest request) {
    request.getSession().setAttribute("CUS_SESSION_ID", id);
    request.getSession().setMaxInactiveInterval(600);
  }

  @Override
  public void giveCusTempSessionToRegister(Cus cus, CusDetails cusDetails, HttpServletRequest request) {
    request.getSession().setAttribute("TEMP_SESSION_CUS_FIRST_NAME", cus.getFirstName());
    request.getSession().setAttribute("TEMP_SESSION_CUS_LAST_NAME", cus.getLastName());
    request.getSession().setAttribute("TEMP_SESSION_CUS_PHONE_NUMBER", cus.getPhoneNumber());
    request.getSession().setAttribute("TEMP_SESSION_CUS_POSTCODE", cusDetails.getPostCode());
    request.getSession().setAttribute("TEMP_SESSION_CUS_ADDRESS_NAME", cusDetails.getAddressName());
    request.getSession().setAttribute("TEMP_SESSION_CUS_ADDRESS_TYPE", cusDetails.getAddressType());
    request.getSession().setAttribute("TEMP_SESSION_CUS_ADDRESS_NUMBER", cusDetails.getDoor());
    request.getSession().setAttribute("TEMP_SESSION_CUS_ADDRESS_BELL", cusDetails.getBell());
    request.getSession().setMaxInactiveInterval(120);
  }

  @Override
  public Cus giveTempCusValues(HttpSession session) {
    Cus cus = new Cus();
    cus.setFirstName((String) session.getAttribute("TEMP_SESSION_CUS_FIRST_NAME"));
    cus.setLastName((String) session.getAttribute("TEMP_SESSION_CUS_LAST_NAME"));
    cus.setPhoneNumber((String) session.getAttribute("TEMP_SESSION_CUS_PHONE_NUMBER"));
    return cus;
  }

  @Override
  public void endCusSession(HttpServletRequest request) {
    request.getSession().invalidate();
  }

  @Override
  public String[] getDataFromDbByQuery(String email) {
    final String query = "SELECT id, email, pwd FROM customers WHERE email = " + "\"" + email + "\"";
    String[] dataByQuery = new String[3];
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        dataByQuery[0] = Integer.toString(rs.getInt("id"));
        dataByQuery[1] = rs.getString("email");
        dataByQuery[2] = rs.getString("pwd");
      }
      ps.close();
      conn.close();
    } catch (ClassNotFoundException CNFe) {
      throw new RuntimeException("JDBC Driver cannot be found.");
    } catch (SQLException SQLe) {
      throw new RuntimeException("SQL Database cannot be found.");
    } catch (Exception e) {
      throw new RuntimeException("Cannot identify the problem.");
    }
    return dataByQuery;
  }

  @Override
  public void setAlreadyRegistered(ErrorMsg errorMsg, RedirectAttributes redirectAttributes) {
    errorMsg.setAlreadyRegistered("alreadyRegistered");
    redirectAttributes.addFlashAttribute("alreadyRegistered", errorMsg.getAlreadyRegistered());
  }

  @Override
  public void setWrongEmail(ErrorMsg errorMsg, RedirectAttributes redirectAttributes) {
    errorMsg.setWrongEmail("wrongEmail");
    redirectAttributes.addFlashAttribute("wrongEmail", errorMsg.getWrongEmail());
  }

  @Override
  public void setWrongPwd(ErrorMsg errorMsg, RedirectAttributes redirectAttributes) {
    errorMsg.setWrongPwd("wrongPwd");
    redirectAttributes.addFlashAttribute("wrongPwd", errorMsg.getWrongPwd());
  }

  @Override
  public boolean isCusHasAccess(HttpSession session) {
    return (session.getAttribute("CUS_SESSION_ID") != null);
  }

  @Override
  public void accessDenied(ErrorMsg errorMsg, RedirectAttributes redirectAttributes) {
    errorMsg.setAccess("accessDenied");
    redirectAttributes.addFlashAttribute("access", errorMsg.getAccess());
  }

  @Override
  public long getCusSessionId(HttpSession session) {
    return (long) session.getAttribute("CUS_SESSION_ID");
  }
}
