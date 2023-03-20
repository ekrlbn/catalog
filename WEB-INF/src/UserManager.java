import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
  private Map<String, User> users;

  public UserManager(Map<String, User> users) {
    this.users = users;
  }

  public UserManager() {
    this.users = new HashMap<String, User>();
  }

  public Map<String, User> getUsers() {
    return users;
  }

  public void userToRegister(User user) {
    if (user.getEmail() == null || user.getLastName() == null || user.getPassword() == null
        || user.getFirstName() == null)
      throw new InvalidParameterException();
    if (user.getFirstName().length() == 0 || user.getLastName().length() == 0 || user.getPassword().length() == 0
        || user.getEmail().length() == 0)
      throw new InvalidParameterException();
    if (users.get(user.getEmail()) != null)
      throw new IllegalStateException();
    users.put(user.getEmail(), user);
  }

  public User loginUser(User user) {
    if (user.getEmail() == null || user.getPassword() == null)
      throw new InvalidParameterException();
    User found = users.get(user.getEmail());
    if (found == null)
      throw new IllegalArgumentException();
    if (users.get(user.getEmail()).getPassword().equals(user.getPassword()) == false)
      throw new IllegalStateException();
    return found; 
  }
  
}
