package liftops;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kopitubruk.util.json.JSONUtil;

import com.google.gson.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WorkoutsServlet extends HttpServlet {

	// servlet for all things needing to get workout information
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		    throws ServletException, IOException {
		
		String function = null;
		
		if (req.getParameterMap().containsKey("function")) {
			function = req.getParameter("function");
		}
		
		if ("insert".equalsIgnoreCase(function)) {
			String workout = req.getParameter("workout");
			String type = req.getParameter("type");
			PrintWriter out = resp.getWriter();
			out.write(insertWorkouts(workout, type));
			return;
		}
		
		if ("remove".equalsIgnoreCase(function)) {
			String workout = req.getParameter("workout");
			PrintWriter out = resp.getWriter();
			out.write(removeWorkouts(workout));
			return;
		}
		
		else {
			// get a list of workouts
			String returnType = req.getParameter("type");
			PrintWriter out = resp.getWriter();
			out.write(getWorkouts(returnType));
			return;
		}
	}
	
	
	private String getWorkouts(String returnType) {
		// return list of workouts and type
		WorkoutsDAO wdao = new WorkoutsDAO();
		List<Workouts> workouts = wdao.findAll();
		String type = null;
		String data = null;
		// loop through workouts and get only the workout name
		Map<String, String> hm = new HashMap<String, String>();
		for (Workouts wo : workouts) {
			if ("category".equalsIgnoreCase(returnType)) {
				data = wo.getCategory();
			}
			else if ("workout".equalsIgnoreCase(returnType)) {
				data = wo.getWorkout();
			}
			// return blank space if nothing matches, will throw an error in jquery
			else {return " ";}
			hm.put(data, data);
		}

		Gson gson = new Gson();
		return gson.toJson(hm);
	}
	
	private int insertWorkouts(String workout, String type) {
		// perform insert in WorkoutsDAO() to workouts table
		WorkoutsDAO wdao = new WorkoutsDAO();
		Workouts woObject = new Workouts();
		woObject.setCategory(type);
		woObject.setWorkout(workout);
		wdao.create(woObject);
		return 0;
	}
	
	private int removeWorkouts(String workout) {
		// perform delete in WorkoutsDAO() to workouts table
		WorkoutsDAO wdao = new WorkoutsDAO();
		Workouts woObject = new Workouts();
		woObject.setWorkout(workout);
		wdao.delete(woObject);
		return 0;
	}
}
