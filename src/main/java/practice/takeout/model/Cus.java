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
  private String pwd;

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

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
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
