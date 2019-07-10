package practice.takeout.model;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = "ORDER_SEQUENCE", sequenceName = "ORDER_SEQ")
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_SEQ")
  @Column(name = "order_id")
  private long id;
  private String status;

  @OneToMany(mappedBy = "cus", cascade = CascadeType.ALL)
  private List<Burger> burgerList;

  public Order() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Burger> getBurgerList() {
    return burgerList;
  }

  public void setBurgerList(List<Burger> burgerList) {
    this.burgerList = burgerList;
  }
}
