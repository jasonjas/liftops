package liftops;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import liftops.PasswordStorage.CannotPerformOperationException;
import liftops.PasswordStorage.InvalidHashException;
import liftops.User;
import liftops.UserDAO;

public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6438774598012638673L;
	private Logger logger = Logger.getLogger(this.getClass());
    private RequestDispatcher jsp;
    
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        jsp = context.getRequestDispatcher("/login.jsp");
     }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
       logger.debug("doGet()");
       jsp.forward(req, resp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
       logger.debug("doPost()");
       
       String username = req.getParameter("username");
       User user = new UserDAO().findByUsername(username);
       if (user == null)
       {
          logger.debug("authentication failed: bad username");
          req.setAttribute("message", "Authentication failed.");
          jsp.forward(req, resp);
          return;
       }
       
       
    // verify password
       String password = req.getParameter("password");
       try {
		if (password == null || !PasswordStorage.verifyPassword(password, user.getPassword()))
		   {
		      logger.debug("authentication failed: bad password");
		      req.setAttribute("message", "Authentication failed.");
		      jsp.forward(req, resp);
		      return;
		   }
	} catch (CannotPerformOperationException | InvalidHashException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

       HttpSession session = req.getSession();
       Long userId = user.getId();
       session.setAttribute("userId", userId);
       logger.debug("authenticated");
       String url = "index.html";
       resp.sendRedirect(url);
    }  
}