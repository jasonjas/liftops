package liftops;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DataAccessObject {

   private static Object idLock = new Object();
   private static BasicDataSource dataSource2 = new BasicDataSource();

   protected static Connection getConnection() 
   {
	   try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		//e1.printStackTrace();
		Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, e1);
	}
      try {    	  
    	  Properties prop = new Properties();
          
          String propFN = "config.properties";
          String pass = null;
          String user = null;
          String db = null;
          InputStream inputStream = DataAccessObject.class.getClassLoader().getResourceAsStream(propFN);
          //InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFN);
          //InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFN);
          
          if (inputStream != null) {
       	   try {
       		   prop.load(inputStream);
   			} catch (IOException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
              pass = prop.getProperty("dbpassword");
              db = prop.getProperty("database");
              user = prop.getProperty("dbuser");
          } else {
              try {
           	   throw new FileNotFoundException("property file '" + propFN + "' not found in the classpath");
   			} catch (FileNotFoundException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
          }
          
	   	   dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
	   	   dataSource2.setUsername(user);
	   	   dataSource2.setPassword(pass);
	   	   dataSource2.setUrl(db);
	   	   dataSource2.setMaxIdle(3);
	   	   dataSource2.setMaxWaitMillis(10000);
         return dataSource2.getConnection();
      } catch (SQLException e) {
    	  Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, e);
         throw new RuntimeException(e);
      }
   }

   protected static void close(Statement statement, Connection connection) 
   {
      close(null, statement, connection);
   }

   protected static void close(ResultSet rs, Statement statement,
         Connection connection) 
   {
      try {
         if (rs != null)
            rs.close();
         if (statement != null)
            statement.close();
         if (connection != null)
            connection.close();
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }

   protected static Long getUniqueId() {
      ResultSet rs = null;
      PreparedStatement statement = null;
      Connection connection = null;
      try 
      {
         connection = getConnection();
         synchronized (idLock) 
         {
            statement = connection.prepareStatement("select next_value from sequence");
            rs = statement.executeQuery();
            rs.first();
            long id = rs.getLong(1);
            statement.close();
            statement = connection.prepareStatement("update sequence set next_value = ?");
            statement.setLong(1, id + 1);
            statement.executeUpdate();
            statement.close();
            return new Long(id);
         }
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
