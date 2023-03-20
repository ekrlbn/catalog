import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;

public class AccessServlet extends HttpServlet {
  private UserManager userManager;
  private DatabaseManager db;
  private static final long serialVersionUID = 100L;

  @Override
   public void init() throws ServletException {
    this.db = new DatabaseManager();
    userManager = new UserManager(this.db.getUsers());
  }

  @Override
  public void destroy() {
    db.writeUsers(this.userManager.getUsers());
  }

  public void loginAction(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    HttpSession session = request.getSession(false);
    if (session != null) {
      String sEmail = (String) session.getAttribute("email");
      if (sEmail != null && email.equals(sEmail)) {
        response.sendRedirect("/catalog/catalog.html");
      } else {
        session.invalidate();
        response.sendRedirect("/catalog/login.html");
      }
    } else {
      try {

        User userToLogin = new User(email, password);
        this.userManager.loginUser(userToLogin);
        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("email", userToLogin.getEmail());
        response.sendRedirect("/catalog/catalog.html");

      } catch (Exception e) {
        String errorResponse = "Looks like there was an error with the user you tried to log in. Make sure that all the fields in the form have some value and are not empty.";
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
        return;
      }
    }

  }

  public void registerAction(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    HttpSession session = request.getSession(false);

    if (session != null) {
      session.invalidate();
      String errorResponse = "Try registering again.";
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
      return;
    }
    try {

      User registeringUser = new User(firstName, lastName, email, password);
      userManager.userToRegister(registeringUser);
      HttpSession newSession = request.getSession(true);
      newSession.setAttribute("email", registeringUser.getEmail());
      response.sendRedirect("/catalog/catalog.html");

    } catch (Exception e) {
      String errorResponse = "Invalid input!";
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
      return;
    }
  }

  public void logoutAction(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    HttpSession session = request.getSession(false);
    if (session != null)
      session.invalidate();
    response.sendRedirect("/catalog/login.html");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    String action = (String) request.getParameter("action");
    if (action == null)
      response.sendError(400, "Invalid Parameter!");
    else if (action.equals("register"))
      registerAction(request, response);
    else if (action.equals("login"))
      loginAction(request, response);
    else if (action.equals("logout"))
      logoutAction(request, response);
    else
      response.sendError(400, "Invalid Parameter!");
  }
}
