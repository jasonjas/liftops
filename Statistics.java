package liftops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Statistics extends DataAccessObject {
	// create statistics
	
	public void updateStats(String workout, int stat, String activityType)
	{
		// updates stats here
		// activityType = cardio / weight
		
		String table = null; // make this activities_cardio or activities_weight below
		
		if (activityType == "cardio") {
			// update activities_cardio table
			table = "activities_cardio";
		}
		
		else if (activityType == "weight") {
			// update activities_weight table
			table = "activities_weight";
		}
		
		else { try {
			throw new Exception("Invalid activity type");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		PreparedStatement statement = null;
		  Connection connection = null;
		  try
		  {
			 connection = getConnection();
			 String sql = "insert into " + table + " (Value) "+ "values (?)";
				 statement = connection.prepareStatement(sql);
			     statement.setInt(1, stat);
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
