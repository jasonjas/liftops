package liftops;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import liftops.PasswordStorage.CannotPerformOperationException;

public class CreateLoginServlet extends HttpServlet {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6977314213774662978L;
	private RequestDispatcher jsp;
	
	public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        jsp = context.getRequestDispatcher("/register.jsp");
     }
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
       //logger.debug("doGet()");
       jsp.forward(req, resp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
       //logger.debug("doPost()");
    	
    	// Get parameters
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	String email = req.getParameter("email");
    	
    	// check if username/password are blank
    	if (username == null || password == null) {
    		//logger.debug("authentication failed: bad password");
            req.setAttribute("message", "Username or password cannot be blank");
            jsp.forward(req, resp);
            return;
    	}
    	UserDAO ud = new UserDAO();
    	User user = new User();
    	User finduser = ud.findByUsername(username);
    	
    	// check if username already exists
    	if (finduser != null) {
    		req.setAttribute("message", "Username already taken");
            jsp.forward(req, resp);
            return;
    	}
    	
    	// create new user and redirect back to login page
    	try {
			user.setUsername(username);
			user.setPassword(PasswordStorage.createHash(password));
			user.setEmail(email);
    	} catch (CannotPerformOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ud.create(user);
        String url = "login";
        resp.sendRedirect(url);
    }
}