package practice.takeout.model;

public class ErrorMsg {
  private String alreadyRegistered;
  private String wrongEmail;
  private String wrongPwd;

  public String getAlreadyRegistered() {
    return alreadyRegistered;
  }

  public void setAlreadyRegistered(String alreadyRegistered) {
    this.alreadyRegistered = alreadyRegistered;
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
}
