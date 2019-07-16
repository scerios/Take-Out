package practice.takeout.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "CUSTOMER_DETAIL_SEQUENCE", sequenceName = "CUSTOMER_DETAIL_SEQ")
@Table(name = "customer_details")
public class CusDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOMER_DETAIL_SEQ")
  @Column(name = "customer_details_id")
  private long id;
  private int postcode;
  private String addressName;
  private String addressType;
  private int door;
  private String bell;
  private String nickname;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cus_id", nullable = false)
  private Cus cus;

  @OneToMany(mappedBy = "cusDetails", cascade = CascadeType.ALL)
  private List<Order> orderList;

  public CusDetails() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getPostcode() {
    return postcode;
  }

  public void setPostcode(int postcode) {
    this.postcode = postcode;
  }

  public String getAddressName() {
    return addressName;
  }

  public void setAddressName(String addressName) {
    this.addressName = addressName;
  }

  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String addressType) {
    this.addressType = addressType;
  }

  public int getDoor() {
    return door;
  }

  public void setDoor(int door) {
    this.door = door;
  }

  public String getBell() {
    return bell;
  }

  public void setBell(String bell) {
    this.bell = bell;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Cus getCus() {
    return cus;
  }

  public void setCus(Cus cus) {
    this.cus = cus;
  }

  public List<Order> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }

  public void addOrder(Order order) {
    if (orderList == null) {
      orderList = new ArrayList<>();
    }
    order.setCusDetails(this);
    orderList.add(order);
  }
}
