package practice.takeout.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "ORDER_DETAILS_SEQUENCE", sequenceName = "ORDER_DETAILS_SEQ")
public class OrderDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_DETAILS_SEQ")
  @Column(name = "order_details_id")
  private long id;
  @Column(name = "customer_detail_id")
  private long cusDetailId;
  @Column(name = "burger_id")
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

  public long getCusDetailId() {
    return cusDetailId;
  }

  public void setCusDetailId(long cusDetailId) {
    this.cusDetailId = cusDetailId;
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
