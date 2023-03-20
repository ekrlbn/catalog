import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;

public class CartServlet extends HttpServlet{
  private CartManager cartManager;
  private DatabaseManager db;
  private static final long serialVersionUID = 100L;
  
  @Override
  public void init() throws ServletException {
    cartManager = new CartManager(db.getUserCarts());
  }

  @Override
  public void destroy(){
    db.writeUserCarts(cartManager.getUserCarts());
  }

}
