package practice.takeout.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "BURGER_SEQUENCE", sequenceName = "BURGER_SEQ")
@Table(name = "burgers")
public class Burger {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "BURGER_SEQ")
  @Column(name = "burger_id")
  private long id;
  private String bun;
  private String meat;
  private String cheese;
  private String sauce;
  private int price;

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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
