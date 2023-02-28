package liftops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class equipmentDAO extends DataAccessObject {
	private static equipmentDAO instance = new equipmentDAO();
	
	public static equipmentDAO getInstance() {
	      return instance;
	}
	
	private equipment read(ResultSet rs) throws SQLException
	   {
	      int id = rs.getInt("id");
	      String wt = rs.getString("eqmnt");
	      String category = rs.getString("type");
	      
	      equipment eqmnt = new equipment();
	      eqmnt.setId(id);
	      eqmnt.setEquipment(wt);
	      eqmnt.setCategory(category);
	      return eqmnt;
	   }
	
	public void create(equipment eqmnt) {
		// create equipment in database
		PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
			 connection = getConnection();
			 String sql = "insert into eqmnts " + "(eqmnt, type) "+ "values (?, ?)";
				 statement = connection.prepareStatement(sql);
				 //statement.setLong(1, id.longValue());
			     statement.setString(1, eqmnt.getEquipment());
			     statement.setString(3, eqmnt.getCategory());
			     statement.executeUpdate();
			  } catch (SQLException e)
			  {
			     throw new RuntimeException(e);
			  } finally
			  {
			     close(statement, connection);
			  }
	}
	
	public void delete(equipment eqmnt) {
		// delete equipment
		  PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
		     connection = getConnection();
		     String sql = "delete from eqmnts where id=?";
		     statement = connection.prepareStatement(sql);
		     int id = eqmnt.getId();
		     statement.setInt(1, id);
		     statement.executeUpdate();
		  } catch (SQLException e)
		  {
		     throw new RuntimeException(e);
		  } finally
		  {
		     close(statement, connection);
		  }
	}
	
	public List<equipment> findAll()
	   {
	      LinkedList<equipment> eqmnts = new LinkedList<equipment>();
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from eqmnts order by id";
	         statement = connection.prepareStatement(sql);
	         rs = statement.executeQuery();
	         while (rs.next())
	         {
	            equipment eqmnt = read(rs);
	            eqmnts.add(eqmnt);
	         }
	         return eqmnts;
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
	
	public equipment findByEquipment(String wt)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from eqmnts where eqmnt=?";
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
	
	public equipment find(int id)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from eqmnts where id=?";
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
