package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;
import practice.takeout.repository.CusRepository;
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
  public String getDataFromDbByQuery(String query) {
    String emailFoundByQuery = "";
    String pwdFoundByQuery = "";
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        if (query.substring(7, 10).equals("pwd")) {
          pwdFoundByQuery = rs.getString("pwd");
        } else {
          emailFoundByQuery = rs.getString("email");
        }
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
    if (query.substring(7, 10).equals("pwd")) {
      return pwdFoundByQuery;
    } else {
      return emailFoundByQuery;
    }
  }

  @Override
  public String generatePinForReference() {
    int random = (int)(Math.random() * 8999999 + 1000000);
    return Integer.toString(random);
  }

  @Override
  public void setTempCusPin(long id, String pin) {
    repository.findById(id).get().setPin(pin);
  }

  @Override
  public String getTempCusPin(long id) {
    return repository.findById(id).get().getPin();
  }

  @Override
  public void delTempCusPin(long id) {
    repository.findById(id).get().setPin(null);
  }
}
