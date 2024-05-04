package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class SignUpDao {
	
	public String createNewUser(String user_type,String user_name,String userpassword,String Email_id,String phone_number,String delivery_address)
	{
		String user_password=encryption(userpassword);
		String insertQuery = String.format("INSERT INTO 510fp.walblues_user_table ( user_type, user_name, user_password, email_id, phone_number, delivery_address) VALUES ( '%s', '%s', '%s', '%s', '%s', '%s')",
                user_type, user_name, user_password, Email_id, phone_number, delivery_address);

		
		
		DbConnection dbconnect=new DbConnection();
		 
		Connection connect = dbconnect.connect();
		
		Statement statement;
		try {
			statement = connect.createStatement();
			int rowsAffected = statement.executeUpdate(insertQuery);
			if(rowsAffected > 0)
			{
				 System.out.println("Data inserted successfully.");
			}
			else 
			{
				 System.out.println("Data inserted failed.");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return null;
	}
	


	public String encryption(String userpassword)
	{
		
		String encypted_pswd=null;
		for (int i = 0; i < userpassword.length(); i++) {
            int ch = (int) userpassword.charAt(i);
            if(i==0) {
            	encypted_pswd=""+ch;
            	}
            else {
            	encypted_pswd+="-"+ch;
            }
        }
		return encypted_pswd;
		
//		String password = userpassword;
//
//        MessageDigest md;
//		try {
//				md = MessageDigest.getInstance("SHA-256");
//				md.update(password.getBytes());
//	
//		        byte byteData[] = md.digest();
//	
//		        StringBuffer sb = new StringBuffer();
//		        for (int i = 0; i < byteData.length; i++) {
//		         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
//		        }
//		        System.out.println("Hex format : " + sb.toString());
//				return sb.toString();
//			}
//		
//			catch (NoSuchAlgorithmException e) 
//			{
//				e.printStackTrace();
//			}
//        return null;
	}
}

