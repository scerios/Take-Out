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
  public String[] getDataFromDbByQuery(String query) {
    String[] dataByQuery = new String[3];
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        String id = Integer.toString(rs.getInt("id"));
        dataByQuery[0] = id;
        String email = rs.getString("email");
        dataByQuery[1] = email;
        String pwd = rs.getString("pwd");
        dataByQuery[2] = pwd;
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
