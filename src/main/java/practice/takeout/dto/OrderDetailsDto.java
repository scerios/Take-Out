package practice.takeout.dto;

public class OrderDetailsDto {
  private long id;
  private String name;
  private String base;
  private String topping;
  private String status;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public String getTopping() {
    return topping;
  }

  public void setTopping(String topping) {
    this.topping = topping;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
