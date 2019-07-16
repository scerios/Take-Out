package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Order;
import practice.takeout.repository.CusDetailsRepository;
import javax.servlet.http.HttpSession;
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
  public void delDetailsById(long id) {
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
    final String query = "SELECT customer_details_id FROM customer_details WHERE cus_id = " + "\"" + cusId + "\"";
    List<Integer> dataByQuery = new ArrayList<>();
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        int extractedId = rs.getInt("customer_details_id");
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

  @Override
  public CusDetails giveTempCusDetailsValues(HttpSession session) {
    CusDetails cusDetails = new CusDetails();
    cusDetails.setPostcode((int) session.getAttribute("TEMP_SESSION_CUS_POSTCODE"));
    cusDetails.setAddressName((String) session.getAttribute("TEMP_SESSION_CUS_ADDRESS_NAME"));
    cusDetails.setAddressType((String) session.getAttribute("TEMP_SESSION_CUS_ADDRESS_TYPE"));
    cusDetails.setDoor((int) session.getAttribute("TEMP_SESSION_CUS_ADDRESS_NUMBER"));
    cusDetails.setBell((String) session.getAttribute("TEMP_SESSION_CUS_ADDRESS_BELL"));
    return cusDetails;
  }

  @Override
  public List<String> getListOfNicknamesFromDbByQuery(long cusId) {
    final String query = "SELECT nickname FROM customer_details WHERE cus_id = " + "\"" + cusId + "\"";
    List<String> listOfNicknames = new ArrayList<>();
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        listOfNicknames.add(rs.getString("nickname"));
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
    return listOfNicknames;
  }

  @Override
  public long getDetailsIdByCusIdAndNickname(long cusId, String nickname) {
    final String query = "SELECT details_id FROM customer_details WHERE cus_id = " + "\"" + cusId + "\""
        + "AND nickname = "  + "\"" + nickname + "\"";
    long extractedId = 0;
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        extractedId = rs.getLong("details_id");
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
    return extractedId;
  }

  @Override
  public void giveSessionTempDetails(CusDetails cusDetails, HttpSession session) {
    session.setAttribute("TEMP_SESSION_CUS_POSTCODE", cusDetails.getPostcode());
    session.setAttribute("TEMP_SESSION_CUS_ADDRESS_NAME", cusDetails.getAddressName());
    session.setAttribute("TEMP_SESSION_CUS_ADDRESS_TYPE", cusDetails.getAddressType());
    session.setAttribute("TEMP_SESSION_CUS_ADDRESS_NUMBER", cusDetails.getDoor());
    session.setAttribute("TEMP_SESSION_CUS_ADDRESS_BELL", cusDetails.getBell());
    session.setMaxInactiveInterval(599);
  }

  @Override
  public void addOrderToDetails(long id, Order order) {
    repository.findById(id).get().addOrder(order);
  }
}
