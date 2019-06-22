package practice.takeout.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "customer_sequence")
@Table(name = "customers")
public class Cus {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
  private long id;
  private String firstName;
  private String lastName;
  private String pwd;

  @OneToMany(mappedBy = "cus", cascade = CascadeType.ALL)
  private List<Address> addressList;

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

  public List<Address> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<Address> addressList) {
    this.addressList = addressList;
  }

  public void addCusAddress(Address address) {
    if (addressList == null) {
      addressList = new ArrayList<>();
    }
    address.setCus(this);
    addressList.add(address);
  }
}
