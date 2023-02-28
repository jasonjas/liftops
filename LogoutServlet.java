package liftops;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 //import org.apache.log4j.Logger;

public class LogoutServlet extends HttpServlet 
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1619357755891233462L;
	//private Logger logger = Logger.getLogger(this.getClass());
   
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
   throws ServletException, IOException {
      //logger.debug("doGet()");
      HttpSession session = req.getSession(false);
      if(session!=null)
    	  session.invalidate();
      
      String url = "login";
      resp.sendRedirect(url);
   }
}