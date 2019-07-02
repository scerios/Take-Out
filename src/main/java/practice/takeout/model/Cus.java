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
  private byte isLoggedIn;

  @OneToMany(mappedBy = "cus", cascade = CascadeType.ALL)
  private List<CusDetails> cusDetailsList;

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

  public byte getIsLoggedIn() {
    return isLoggedIn;
  }

  public void setIsLoggedIn(byte isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
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
}
