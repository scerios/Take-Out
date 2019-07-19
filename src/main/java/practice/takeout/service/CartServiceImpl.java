package practice.takeout.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.dataProvider.SystemDefaults;
import practice.takeout.dto.BurgerQuantityDto;
import practice.takeout.dto.CartBurgerDto;
import practice.takeout.model.Cart;
import practice.takeout.model.PopUpMsq;
import practice.takeout.repository.CartRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
  private SystemDefaults defaults = new SystemDefaults();
  private CartRepository repository;
  private final String JDBC_DRIVER = defaults.getJdbcDriver();
  private final String DB_URL = defaults.getDbUrl();
  private final String USERNAME = defaults.getUserName();
  private final String PWD = defaults.getPwd();

  public CartServiceImpl(CartRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addBurgerToCart(Cart cart, long burgerId, int quantity) {
    cart.setBurgerId(burgerId);
    cart.setQuantity(quantity);
    repository.save(cart);
  }

  @Override
  public void setBurgerAdded(PopUpMsq popUpMsq, RedirectAttributes redirectAttributes) {
    popUpMsq.setAddedToCart("addedToCart");
    redirectAttributes.addFlashAttribute("addedToCart", popUpMsq.getAddedToCart());
  }

  @Override
  public List<CartBurgerDto> findAllByCusId(long id) {
    final String query = "SELECT bun, cheese, meat, sauce, quantity FROM CART INNER JOIN burgers ON cart.burger_id = burgers.burger_id WHERE cus_id = " + "\"" + id + "\"";
    List<CartBurgerDto> cartIterable = new ArrayList<>();
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        CartBurgerDto cartBurgerDto = new CartBurgerDto();
        cartBurgerDto.setBun(rs.getString("bun"));
        cartBurgerDto.setMeat(rs.getString("meat"));
        cartBurgerDto.setCheese(rs.getString("cheese"));
        cartBurgerDto.setSauce(rs.getString("sauce"));
        cartBurgerDto.setQuantity(rs.getInt("quantity"));
        cartIterable.add(cartBurgerDto);
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
    return cartIterable;
  }

  @Override
  public List<BurgerQuantityDto> getBurgerIdsAndQuantitiesFromCartByCusId(long id) {
    final String query = "SELECT burger_id, quantity FROM CART WHERE cus_id = " + "\"" + id + "\"";
    List<BurgerQuantityDto> burgerQuantities = new ArrayList<>();
    PreparedStatement ps;
    Connection conn;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);
      ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery(query);
      while (rs.next()) {
        BurgerQuantityDto burgerQuantityDto = new BurgerQuantityDto();
        burgerQuantityDto.setBurgerId(rs.getLong("burger_id"));
        burgerQuantityDto.setQuantity(rs.getInt("quantity"));
        burgerQuantities.add(burgerQuantityDto);
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
    return burgerQuantities;
  }

  @Override
  public void deleteAllByCus_Id(long id) {
    repository.deleteAllByCus_Id(id);
  }
}
