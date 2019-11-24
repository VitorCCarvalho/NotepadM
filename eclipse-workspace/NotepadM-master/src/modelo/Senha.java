package modelo;

public class Senha {
  
  private String hash;
  private String salt;
  
  public String getHash() {
    return hash;
  }
  
  public Senha(String hash, String salt) {
    this.hash = hash;
    this.salt = salt;
  }
  
  public void setHash(String hash) {
    this.hash = hash;
  }
  public String getSalt() {
    return salt;
  }
  public void setSalt(String salt) {
    this.salt = salt;
  }

}
