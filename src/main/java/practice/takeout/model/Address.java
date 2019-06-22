package practice.takeout.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "address_sequence")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
  @Column(name = "address_id")
  private long id;
  private int postCode;
  private String street;
  private int floor;
  private String door;
  private String bell;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cus_id", nullable = false)
  private Cus cus;

  public Address() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getPostCode() {
    return postCode;
  }

  public void setPostCode(int postCode) {
    this.postCode = postCode;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public int getFloor() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public String getDoor() {
    return door;
  }

  public void setDoor(String door) {
    this.door = door;
  }

  public String getBell() {
    return bell;
  }

  public void setBell(String bell) {
    this.bell = bell;
  }

  public Cus getCus() {
    return cus;
  }

  public void setCus(Cus cus) {
    this.cus = cus;
  }
}
