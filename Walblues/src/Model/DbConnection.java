package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	final String url = "jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useSSL=false";
	final String username = "fp510";
	final String password = "510";

	public Connection connect() 
	{
		try{
			Connection conn = DriverManager.getConnection(url, username, password);
			if (conn != null) {
				System.out.println("Connected to the database!");
				return conn;
			} 
			else {
				System.out.println("Failed to make connection!");
				
			}
		}
		catch (SQLException e) {
			System.out.println("Failed to connect to the database!");
			e.printStackTrace();
		}
		return null;
	}
	
	public void Disconnect(Connection conn)
	{
		try 
		{
			if(conn != null)
			{
				conn.close();
				System.out.println("DataBase Disconnected sucessfully!!");
			}
		}
		catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}
}

