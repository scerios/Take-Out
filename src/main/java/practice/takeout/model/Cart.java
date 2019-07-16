package practice.takeout.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "CART_SEQUENCE", sequenceName = "CART_SEQ")
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "CART_SEQ")
  private long id;
  private long burgerId;
  private int quantity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cus_id", nullable = false)
  private Cus cus;

  public Cart() {
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

  public Cus getCus() {
    return cus;
  }

  public void setCus(Cus cus) {
    this.cus = cus;
  }
}
