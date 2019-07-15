package practice.takeout.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
  @CreationTimestamp
  @Column(name = "time_of_status_set")
  private Date timeOfStatusSet;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cus_id", nullable = false)
  private Cus cus;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderDetails> orderDetailsList;

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

  public Date getTimeOfStatusSet() {
    return timeOfStatusSet;
  }

  public void setTimeOfStatusSet(Date timeOfStatusSet) {
    this.timeOfStatusSet = timeOfStatusSet;
  }

  public Cus getCus() {
    return cus;
  }

  public void setCus(Cus cus) {
    this.cus = cus;
  }

  public List<OrderDetails> getOrderDetailsList() {
    return orderDetailsList;
  }

  public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
    this.orderDetailsList = orderDetailsList;
  }

  public void addOrderDetails(OrderDetails orderDetails) {
    if (orderDetailsList == null) {
      orderDetailsList = new ArrayList<>();
    }
    orderDetails.setOrder(this);
    orderDetailsList.add(orderDetails);
  }
}
