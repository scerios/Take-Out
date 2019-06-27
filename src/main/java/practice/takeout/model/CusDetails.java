package practice.takeout.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "DETAILS_SEQUENCE", sequenceName = "DETAILS_SEQ")
public class CusDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "DETAILS_SEQ")
  @Column(name = "details_id")
  private long id;
  private int postCode;
  private String addressName;
  private String addressType;
  private int door;
  private String bell;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cus_id", nullable = false)
  private Cus cus;

  public CusDetails() {
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

  public String getAddressName() {
    return addressName;
  }

  public void setAddressName(String addressName) {
    this.addressName = addressName;
  }

  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String addressType) {
    this.addressType = addressType;
  }

  public int getDoor() {
    return door;
  }

  public void setDoor(int door) {
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
