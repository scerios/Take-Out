package practice.takeout.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;
import practice.takeout.model.ErrorMsg;
import practice.takeout.repository.CusRepository;
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
    repository.findById(cus.getId()).get().setIsLoggedIn((byte) 0);
    repository.save(repository.findById(cus.getId()).get());
  }

  @Override
  public void updCus(Cus cus) {
    repository.save(cus);
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
  public byte getIsLoggedIn(long id) {
    return repository.findById(id).get().getIsLoggedIn();
  }

  @Override
  public void setIsLoggedIn(long id, byte isLoggedIn) {
    repository.findById(id).get().setIsLoggedIn(isLoggedIn);
    repository.save(repository.findById(id).get());
  }

  @Override
  public String[] getDataFromDbByQuery(String email) {
    final String query = "SELECT * FROM customers WHERE email = " + "\"" + email + "\"";
    String[] dataByQuery = new String[3];
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        String extractedId = Integer.toString(rs.getInt("id"));
        dataByQuery[0] = extractedId;
        String extractedEmail = rs.getString("email");
        dataByQuery[1] = extractedEmail;
        String extractedPwd = rs.getString("pwd");
        dataByQuery[2] = extractedPwd;
      }
      ps.close();
      conn.close();
    } catch (ClassNotFoundException CNFe) {
      throw new RuntimeException("JDBC Driver cannot be found.");
    } catch (SQLException SQLe) {
      SQLe.printStackTrace();
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
  public void setAlreadyLoggedIn(ErrorMsg errorMsg, RedirectAttributes redirectAttributes) {
    errorMsg.setAlreadyLoggedIn("alreadyLoggedIn");
    redirectAttributes.addFlashAttribute("alreadyLoggedIn", errorMsg.getAlreadyLoggedIn());
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
    if (session.getAttribute("CUS_SESSION_ID") == null) {
      return false;
    } else {
      return true;
    }
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
