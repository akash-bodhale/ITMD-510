package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginDao {
	
	public String postValidation(String username, String paswd)
	{	
		String sqlQuery="Select id,user_name,user_password,user_type from walblues_user_table";
		
		DbConnection dbconnect=new DbConnection();
		 
		Connection connect = dbconnect.connect();
		
		try 
		{
			Statement statement= connect.createStatement();
			ResultSet rs= statement.executeQuery(sqlQuery);
			while(rs.next()) 
			{	
				String user_name = rs.getString("user_name");
				String password = rs.getString("user_password");
				String user_type=rs.getString("user_type");
				String dcrpt_pswd = decryption(rs.getString("user_password"));
				
				if(user_name.equals(username) && dcrpt_pswd.equals(paswd))
				{
					if(user_type.equals("admin"))
					{
						return "Welcome admin"+ user_name;
						
					}
					
					else
					{
						return "Welcome "+user_name;
					}
				}	
			}
			
			dbconnect.Disconnect(connect);
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return "User name and password not matched.try again!!";	
	}
	
	
	public String decryption(String user_password)
	{	
		System.out.println(user_password);
	    
		String[] asciiValues = user_password.split("-");
		StringBuilder result = new StringBuilder();
        for (String asciiValue : asciiValues) {
            int value = Integer.parseInt(asciiValue);
            char ch = (char) value;
            result.append(ch);
        }
        System.out.println("Original string: " + result.toString());   
	    return result.toString();
	}
	
}

