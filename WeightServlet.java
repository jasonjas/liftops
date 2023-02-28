package liftops;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeightServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6422422459390740382L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		    throws ServletException, IOException {
		
		// Get weight action and update values
		String action="query";
		String wval=null;
		String cval=null;
		action = req.getParameter("action");
		wval = req.getParameter("weight");
		cval = req.getParameter("count");
		
		// query results for both query action and update action
		
		WeightDAO wdao = new WeightDAO();
		
		List<Weight> weights = wdao.findAll();
		
		// update weights if action is 'update'
		if (action == "update") {
			// parse weights list (from above) and check if wval/cval exist or equal
			for (ListIterator<Weight> iter = weights.listIterator(); iter.hasNext();) {
				
				// show if this is where the first record is or after the iter.next() command
				
				Weight element = iter.next();
			}
		}
	}
}
