package practice.takeout.model;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = "BURGER_SEQUENCE", sequenceName = "BURGER_SEQ")
public class Burger {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "BURGER_SEQ")
  @Column(name = "burger_id")
  private long id;
  private String bun;
  private String meat;
  private String cheese;
  private String sauce;
  private List<String> extras;
  private int amount;
  private int price;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  public Burger() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBun() {
    return bun;
  }

  public void setBun(String bun) {
    this.bun = bun;
  }

  public String getMeat() {
    return meat;
  }

  public void setMeat(String meat) {
    this.meat = meat;
  }

  public String getCheese() {
    return cheese;
  }

  public void setCheese(String cheese) {
    this.cheese = cheese;
  }

  public String getSauce() {
    return sauce;
  }

  public void setSauce(String sauce) {
    this.sauce = sauce;
  }

  public List<String> getExtras() {
    return extras;
  }

  public void setExtras(List<String> extras) {
    this.extras = extras;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
