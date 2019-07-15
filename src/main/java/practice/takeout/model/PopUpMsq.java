package practice.takeout.model;

public class PopUpMsq {
  private String alreadyRegistered;
  private String alreadyLoggedIn;
  private String wrongEmail;
  private String wrongPwd;
  private String access;
  private String addedToCart;

  public PopUpMsq() {
  }

  public String getAlreadyRegistered() {
    return alreadyRegistered;
  }

  public void setAlreadyRegistered(String alreadyRegistered) {
    this.alreadyRegistered = alreadyRegistered;
  }

  public String getAlreadyLoggedIn() {
    return alreadyLoggedIn;
  }

  public void setAlreadyLoggedIn(String alreadyLoggedIn) {
    this.alreadyLoggedIn = alreadyLoggedIn;
  }

  public String getWrongEmail() {
    return wrongEmail;
  }

  public void setWrongEmail(String wrongEmail) {
    this.wrongEmail = wrongEmail;
  }

  public String getWrongPwd() {
    return wrongPwd;
  }

  public void setWrongPwd(String wrongPwd) {
    this.wrongPwd = wrongPwd;
  }

  public String getAccess() {
    return access;
  }

  public void setAccess(String access) {
    this.access = access;
  }

  public String getAddedToCart() {
    return addedToCart;
  }

  public void setAddedToCart(String addedToCart) {
    this.addedToCart = addedToCart;
  }
}
