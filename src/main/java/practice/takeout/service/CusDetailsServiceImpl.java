package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.model.CusDetails;
import practice.takeout.repository.CusDetailsRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CusDetailsServiceImpl implements CusDetailsService {
  private SystemDefaults defaults = new SystemDefaults();
  private CusDetailsRepository repository;
  private final String JDBC_DRIVER = defaults.getJdbcDriver();
  private final String DB_URL = defaults.getDbUrl();
  private final String USERNAME = defaults.getUserName();
  private final String PWD = defaults.getPwd();

  CusDetailsServiceImpl(CusDetailsRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addDetails(CusDetails cusDetails) {
    repository.save(cusDetails);
  }

  @Override
  public void deleteDetailsById(long id) {
    repository.deleteById(id);
  }

  @Override
  public CusDetails getDetailsById(long id) {
    return repository.findById(id).get();
  }

  @Override
  public Iterable<CusDetails> findAllByCus_Id(long id) {
    return repository.findAllByCus_Id(id);
  }

  @Override
  public boolean isCusHasAccessToDetails(long cusId, long cusDetailsId) {
    final String query = "SELECT details_id FROM cus_details WHERE cus_id = " + "\"" + cusId + "\"";
    List<Integer> dataByQuery = new ArrayList<>();
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        int extractedId = rs.getInt("details_id");
        dataByQuery.add(extractedId);
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
    for (int extractedId : dataByQuery) {
      if (extractedId == cusDetailsId) {
        return true;
      }
    }
    return false;
  }
}
