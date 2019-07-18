package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Order;
import practice.takeout.model.OrderDetails;
import practice.takeout.repository.OrderRepository;
import java.sql.*;

@Service
public class OrderServiceImpl implements OrderService {
  private SystemDefaults defaults = new SystemDefaults();
  private OrderRepository repository;
  private final String JDBC_DRIVER = defaults.getJdbcDriver();
  private final String DB_URL = defaults.getDbUrl();
  private final String USERNAME = defaults.getUserName();
  private final String PWD = defaults.getPwd();

  public OrderServiceImpl(OrderRepository repository) {
    this.repository = repository;
  }

  @Override
  public Order addOrder(CusDetails cusDetails) {
    Order order = new Order();
    order.setCusDetails(cusDetails);
    order.setStatus("ordered");
    repository.save(order);
    return order;
  }

  @Override
  public void addDetailsToOrder(long id, OrderDetails orderDetails) {
    repository.findById(id).get().addOrderDetails(orderDetails);
  }

  @Override
  public Order getLastOrderByCusDetailsId(long id) {
    final String query = "SELECT order_id FROM orders WHERE customer_details_id = " + "\"" + id + "\""
        + "ORDER BY order_id DESC LIMIT 1";
    Order order = new Order();
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        order = repository.findById(rs.getLong("order_id")).get();
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
    return order;
  }
}
