package practice.takeout.dto;

public class CartBurgerDto {
  private String bun;
  private String meat;
  private String cheese;
  private String sauce;
  private int quantity;

  public CartBurgerDto() {
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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
