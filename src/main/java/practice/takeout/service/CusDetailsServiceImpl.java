package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.model.CusDetails;
import practice.takeout.repository.CusDetailsRepository;
import java.sql.*;

@Service
public class CusDetailsServiceImpl implements CusDetailsService {
  private SystemDefaults defaults = new SystemDefaults();
  private CusDetailsRepository repository;
  final String JDBC_DRIVER = defaults.getJdbcDriver();
  final String DB_URL = defaults.getDbUrl();
  final String USERNAME = defaults.getUserName();
  final String PWD = defaults.getPwd();

  CusDetailsServiceImpl(CusDetailsRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addDetails(CusDetails cusDetails) {
    repository.save(cusDetails);
  }

  @Override
  public CusDetails getDetailsById(long id) {
    return repository.findById(id).get();
  }

  @Override
  public boolean isEmailAlreadyRegistered(String email) {
    final String lookForEmail = "SELECT email FROM cus_details WHERE email = " + "\"" + email + "\"";
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
