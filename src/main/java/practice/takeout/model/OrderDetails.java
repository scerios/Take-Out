package practice.takeout.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "ORDER_DETAIL_SEQUENCE", sequenceName = "ORDER_DETAIL_SEQ")
public class OrderDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_DETAIL_SEQ")
  @Column(name = "order_details_id")
  private long id;
  private long burgerId;
  private int quantity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  public OrderDetails() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getBurgerId() {
    return burgerId;
  }

  public void setBurgerId(long burgerId) {
    this.burgerId = burgerId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
