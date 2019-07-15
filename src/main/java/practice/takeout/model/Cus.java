package practice.takeout.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Cus {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String firstName;
  private String lastName;
  private String email;
  private String pwd;
  private String phoneNumber;

  @OneToMany(mappedBy = "cus", cascade = CascadeType.ALL)
  private List<CusDetails> cusDetailsList;

  @OneToMany(mappedBy = "cus", cascade = CascadeType.ALL)
  private List<Cart> cartList;

  public Cus() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public List<CusDetails> getCusDetailsList() {
    return cusDetailsList;
  }

  public void setCusDetailsList(List<CusDetails> cusDetailsList) {
    this.cusDetailsList = cusDetailsList;
  }

  public void addCusDetails(CusDetails cusDetails) {
    if (cusDetailsList == null) {
      cusDetailsList = new ArrayList<>();
    }
    cusDetails.setCus(this);
    cusDetailsList.add(cusDetails);
  }

  public List<Cart> getCartList() {
    return cartList;
  }

  public void setCartList(List<Cart> cartList) {
    this.cartList = cartList;
  }

  public void addToCart(Cart cart) {
    if (cartList == null) {
      cartList = new ArrayList<>();
    }
    cart.setCus(this);
    cartList.add(cart);
  }
}
