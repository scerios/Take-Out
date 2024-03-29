package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.model.Burger;
import practice.takeout.repository.BurgerRepository;
import java.sql.*;

@Service
public class BurgerServiceImpl implements BurgerService {
  private SystemDefaults defaults = new SystemDefaults();
  private BurgerRepository repository;
  private final String JDBC_DRIVER = defaults.getJdbcDriver();
  private final String DB_URL = defaults.getDbUrl();
  private final String USERNAME = defaults.getUserName();
  private final String PWD = defaults.getPwd();

  public BurgerServiceImpl(BurgerRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addBurger(Burger burger) {
    double price = 0;
    switch (burger.getBun()) {
      case "Ciabatta":
        price = 1;
        break;
      case "Gluten Free":
        price = 1.5;
        break;
      default:
        price = 1.2;
    }

    switch (burger.getMeat()) {
      case "Classic Beef":
        price += 3;
        break;
      case "Chicken":
        price += 2;
        break;
      case "Pulled Pork":
        price += 2.5;
        break;
      case "Tofu":
        price += 1.5;
        break;
    }

    switch (burger.getCheese()) {
      case "Camembert":
        price += 2.3;
        break;
      case "Gorgonzola":
        price += 2.6;
        break;
      default:
        price += 2;
    }
    burger.setPrice(price);
    repository.save(burger);
  }

  @Override
  public Burger getBurgerById(long id) {
    return repository.findById(id).get();
  }

  @Override
  public long getBurgerIdByQuery(String bun, String meat, String cheese, String sauce) {
    final String query = "SELECT burger_id FROM burgers WHERE bun = " + "\"" + bun + "\"" + "AND meat = " +
        "\"" + meat + "\"" + "AND cheese = " + "\"" + cheese + "\"" + "AND sauce = " + "\"" + sauce + "\"";
    long extractedId = 0;
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        extractedId = rs.getLong("burger_id");
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
  public double getPriceById(long id) {
    return repository.findById(id).get().getPrice();
  }
}
