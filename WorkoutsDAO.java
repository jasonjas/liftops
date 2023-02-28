package liftops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class WorkoutsDAO extends DataAccessObject {

	private static WorkoutsDAO instance = new WorkoutsDAO();
	
	public static WorkoutsDAO getInstance() {
	      return instance;
	}
	
	private Workouts read(ResultSet rs) throws SQLException
	   {
	      int id = rs.getInt("id");
	      String wt = rs.getString("workout");
	      String category = rs.getString("type");
	      
	      Workouts workout = new Workouts();
	      workout.setId(id);
	      workout.setWorkout(wt);
	      workout.setCategory(category);
	      return workout;
	   }
	
	public void create(Workouts workout) {
		// create workout in database
		PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
			 connection = getConnection();
			 String sql = "insert into workouts " + "(workout, type) "+ "values (?, ?)";
				 statement = connection.prepareStatement(sql);
				 //statement.setLong(1, id.longValue());
			     statement.setString(1, workout.getWorkout());
			     statement.setString(2, workout.getCategory());
			     statement.executeUpdate();
			  } catch (SQLException e)
			  {
			     throw new RuntimeException(e);
			  } finally
			  {
			     close(statement, connection);
			  }
	}
	
	public void delete(Workouts workout) {
		// delete workout
		  PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
		     connection = getConnection();
		     String sql = "delete from workouts where workout=?";
		     statement = connection.prepareStatement(sql);
		     String wo = workout.getWorkout();
		     statement.setString(1, wo);
		     statement.executeUpdate();
		  } catch (SQLException e)
		  {
		     throw new RuntimeException(e);
		  } finally
		  {
		     close(statement, connection);
		  }
	}
	
	public List<Workouts> findAll()
	   {
	      LinkedList<Workouts> workouts = new LinkedList<Workouts>();
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from workouts order by id";
	         statement = connection.prepareStatement(sql);
	         rs = statement.executeQuery();
	         while (rs.next())
	         {
	            Workouts workout = read(rs);
	            workouts.add(workout);
	         }
	         return workouts;
	      }
	      catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      }
	      finally
	      {
	         close(rs, statement, connection);
	      }
	   }
	
	public List<String> findAllJson()
	   {
	      LinkedList<String> workouts = new LinkedList<String>();
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from workouts order by id";
	         statement = connection.prepareStatement(sql);
	         rs = statement.executeQuery();
	         while (rs.next())
	         {
	            Workouts workout = read(rs);
	            workouts.add("workout:" + workout.getWorkout());
	         }
	         return workouts;
	      }
	      catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      }
	      finally
	      {
	         close(rs, statement, connection);
	      }
	   }
	
	public Workouts findByWorkout(String wt)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from workouts where workout=?";
	         statement = connection.prepareStatement(sql);
	         statement.setString(1, wt);
	         rs = statement.executeQuery();
	         if (!rs.next())
	         {
	            return null;
	         }
	         return read(rs);
	      }
	      catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      }
	      finally
	      {
	         close(rs, statement, connection);
	      }
	   }
	
	public Workouts find(int id)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from workouts where id=?";
	         statement = connection.prepareStatement(sql);
	         statement.setInt(1, id);
	         rs = statement.executeQuery();
	         if (!rs.next())
	         {
	            return null;
	         }
	         return read(rs);
	      }
	      catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      }
	      finally
	      {
	         close(rs, statement, connection);
	      }
	   }
}
