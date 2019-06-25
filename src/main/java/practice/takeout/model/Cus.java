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
  private String email;
  private String pwd;
  private String pin;

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

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
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
