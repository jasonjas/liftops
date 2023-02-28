package liftops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class WeightDAO extends DataAccessObject {
	private static WeightDAO instance = new WeightDAO();
	
	public static WeightDAO getInstance() {
	      return instance;
	}
	
	private Weight read(ResultSet rs) throws SQLException
	   {
	      Long id = new Long(rs.getLong("id"));
	      int wt = rs.getInt("weight");
	      int count = rs.getInt("count");
	      String category = rs.getString("type");
	      
	      Weight weight = new Weight();
	      weight.setId(id);
	      weight.setWeight(wt);
	      weight.setCount(count);
	      weight.setCategory(category);
	      return weight;
	   }
	
	public Weight find(Long id)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from weight where id=?";
	         statement = connection.prepareStatement(sql);
	         statement.setLong(1, id.longValue());
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
	
	public Weight findByWeight(int wt)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from weight where weight=?";
	         statement = connection.prepareStatement(sql);
	         statement.setInt(1, wt);
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
	
	 public List<Weight> findAll()
	   {
	      LinkedList<Weight> weights = new LinkedList<Weight>();
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from weight order by id";
	         statement = connection.prepareStatement(sql);
	         rs = statement.executeQuery();
	         while (rs.next())
	         {
	            Weight weight = read(rs);
	            weights.add(weight);
	         }
	         return weights;
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
	
	public void create(Weight weight) {
		// create weights in database
		PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
			 connection = getConnection();
			 String sql = "insert into weight " + "(weight, count, type) "+ "values (?, ?, ?)";
				 statement = connection.prepareStatement(sql);
				 //statement.setLong(1, id.longValue());
			     statement.setInt(1, weight.getWeight());
			     statement.setInt(2, weight.getCount());
			     statement.setString(3, weight.getCategory());
			     statement.executeUpdate();
			  } catch (SQLException e)
			  {
			     throw new RuntimeException(e);
			  } finally
			  {
			     close(statement, connection);
			  }
	}
	
	public void delete(Weight weight) {
		// delete weight
		  PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
		     connection = getConnection();
		     String sql = "delete from weight where id=?";
		     statement = connection.prepareStatement(sql);
		     Long id = weight.getId();
		     statement.setLong(1, id.longValue());
		     statement.executeUpdate();
		  } catch (SQLException e)
		  {
		     throw new RuntimeException(e);
		  } finally
		  {
		     close(statement, connection);
		  }
	}
	
	public void update(Weight weight)
	   {
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "update weight set " + "count=? where id=?";
	         statement = connection.prepareStatement(sql);
	         statement.setInt(1, weight.getCount());
	         statement.setLong(2, weight.getId());
	         statement.executeUpdate();
	      } catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      } finally
	      {
	         close(statement, connection);
	      }
	   }
	
	public void addType(Weight weight) {
		// adds a category
		PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
			 connection = getConnection();
			 String sql = "insert into weighttype " + "(type) " + "values (?)";
			 statement = connection.prepareStatement(sql);
			 statement.setString(1, weight.getCategory());
			 statement.executeUpdate();
			 
		  } catch (SQLException e)
		  {
		     throw new RuntimeException(e);
		  } finally
		  {
		     close(statement, connection);
		  }
	}
}
