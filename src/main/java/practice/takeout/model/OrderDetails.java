package practice.takeout.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "ORDER_DETAILS_SEQUENCE", sequenceName = "ORDER_DETAILS_SEQ")
public class OrderDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_DETAILS_SEQ")
  @Column(name = "order_details_id")
  private long id;
  @Column(name = "order_id")
  private long orderId;
  @Column(name = "burger_id")
  private long burgerId;
  private int amountOfBurger;

  public OrderDetails() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public long getBurgerId() {
    return burgerId;
  }

  public void setBurgerId(long burgerId) {
    this.burgerId = burgerId;
  }

  public int getAmountOfBurger() {
    return amountOfBurger;
  }

  public void setAmountOfBurger(int amountOfBurger) {
    this.amountOfBurger = amountOfBurger;
  }
}
