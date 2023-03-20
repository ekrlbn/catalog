import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CartManager {
  private Map<String, Map<CartItem, Integer>> userCarts;

  public CartManager(Map<String, Map<CartItem, Integer>> userCarts) {
    this.userCarts = userCarts;
  }

  public CartManager() {
    this.userCarts = new HashMap<>();
  }

  public Map<String, Map<CartItem, Integer>> getUserCarts() {
    return userCarts;
  }

  public Map<CartItem, Integer> getUserCart(String email) {
    return userCarts.get(email);
  }

  public void addToCart(String email, CartItem item) {
    if (userCarts.get(email) == null)
      userCarts.put(email, new HashMap<CartItem, Integer>());

    Map<CartItem, Integer> userCart = userCarts.get(email);
    if (userCart.get(item) == null)
      userCart.put(item, 1);
    else
      userCart.put(item, ((int) userCart.get(item) + 1));

  }

  public static void main(String[] args) {
    CartManager manager = new CartManager();
    User user1 = new User("first", "last", "mail@", "2134");
    User user2 = new User("first2", "last2", "mail2@", "2134");
    UserManager userManager = new UserManager();
    userManager.userToRegister(user1);
    userManager.userToRegister(user2);

    
    DatabaseManager db = new DatabaseManager();
    System.out.println("writing users...");
    db.writeUsers(userManager.getUsers());

    System.out.println("reading users...");
    UserManager manager2 = new UserManager(db.getUsers());
    manager2.getUsers().forEach((key,value)->{
      System.out.println(value.getFirstName() + ", " + value.getLastName() + ", " + value.getEmail());
    });

    // CartItem item1 = new CartItem("pencil", "/img2", 12);
    // CartItem item2 = new CartItem("ball", "/img2", 20);
    // manager.addToCart(user1.getEmail(), item2);
    // manager.addToCart(user1.getEmail(), item2);
    // manager.addToCart(user1.getEmail(), item1);
    // manager.addToCart(user2.getEmail(), item2);

    // for (Entry<String, Map<CartItem, Integer>> entry : manager.getUserCarts().entrySet()) {

    //   System.out.println(entry.getKey());
    //   for (Entry<CartItem, Integer> item : entry.getValue().entrySet())
    //     System.out.println("\t" + item.getKey().getName() + ": " + item.getValue());
    // }

  }
}
