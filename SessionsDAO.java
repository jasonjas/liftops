package liftops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessionsDAO extends DataAccessObject {
	private static SessionsDAO instance = new SessionsDAO();
	
	public static SessionsDAO getInstance() {
	      return instance;
	}
	
	private void createSession(Sessions session) {
		// create session in database
		PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
			 connection = getConnection();
			 String sql = "insert into sessions " + "(userid, starttime, endtime, date, description) "+ "values (?, ?, ?, ?, ?)";
				 statement = connection.prepareStatement(sql);
			     statement.setInt(1, session.getUserId());
			     statement.setString(2, session.getStartDate());
			     statement.setString(3, session.getEndDate());
			     statement.setString(4, session.getDate());
			     statement.setString(5, session.getDescription());
			     statement.executeUpdate();
			  } catch (SQLException e)
		  {
		     throw new RuntimeException(e);
		  } finally
		  {
		     close(statement, connection);
		  }
	}
	
	private void closeSession(Sessions session) {
		PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
			 connection = getConnection();
			 String sql = "insert into sessions " + "(userid, starttime, endtime, date, description) "+ "values (?, ?, ?, ?, ?)";
				 statement = connection.prepareStatement(sql);
			     statement.setInt(1, session.getUserId());
			     statement.setString(2, session.getStartDate());
			     statement.setString(3, session.getEndDate());
			     statement.setString(4, session.getDate());
			     statement.setString(5, session.getDescription());
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
