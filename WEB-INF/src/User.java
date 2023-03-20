import java.io.Serializable;

public class User implements Serializable {
  static final long serialVersionUID = 1L;

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  
  public User(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return this.getFirstName() + ", " + this.getLastName() + ", " + this.getEmail(); 
  }

  public boolean equals(User user) {
    if (user == null || user.getClass() != this.getClass())
      return false;
    return this.email.equals(user.getEmail());
  }

  public int hashCode() {
    return this.email.hashCode();
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
