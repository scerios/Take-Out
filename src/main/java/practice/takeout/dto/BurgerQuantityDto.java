package practice.takeout.dto;

public class BurgerQuantityDto {
  private long burgerId;
  private int quantity;

  public BurgerQuantityDto() {
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
}
