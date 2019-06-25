package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;
import practice.takeout.repository.CusRepository;
import java.sql.*;
;

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
  public boolean isEmailAlreadyRegistered(String email) {
    final String lookForEmail = "SELECT email FROM customers WHERE email = " + "\"" + email + "\"";
    boolean isRegistered = false;
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(lookForEmail);
      ResultSet rs = ps.executeQuery(lookForEmail);
      while (rs.next()) {
        String result = rs.getString("email");
        if (result.equals(email)) {
          isRegistered = true;
        }
      }
      ps.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return isRegistered;
  }
}
