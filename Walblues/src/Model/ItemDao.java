package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemDao {

    public ObservableList<Product> retriveItem() {
        String Sql_retrive = "Select * from walblues_product_table";
        DbConnection dbconnect = new DbConnection();
        Connection connect = dbconnect.connect();
        ObservableList<Product> product_data = FXCollections.observableArrayList(); 

        try {
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(Sql_retrive);

            while (rs.next()) {
            	System.out.println(rs.getString("category"));
                Product data = new Product();
                Integer id=rs.getInt("id");
                System.out.println(id.toString());
                data.setProductID(id.toString());
                data.setProductName(rs.getString("name"));
                data.setCategory(rs.getString("category"));
                data.setPrice(rs.getString("price"));
                data.setQuantity(rs.getString("available_qty"));
                product_data.add(data);
            }

            dbconnect.Disconnect(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product_data; 
    }
    
    
    public void Insert_order(String Name, String Quantity, String Price, String CustomerName) {
        String sql = "INSERT INTO walblues_order_table (product_name, product_quatity, price, customer_name) VALUES (?,?,?,?)";
        DbConnection dbconnect = new DbConnection();
        Connection connect = dbconnect.connect();
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Quantity);
            preparedStatement.setString(3, Price);
            preparedStatement.setString(4, CustomerName);
            preparedStatement.executeUpdate();
            dbconnect.Disconnect(connect);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void Update_stock(String name,String qnty)
    {
    	String selectsql="Select name , available_qty from walblues_product_table";
    	String sql = "UPDATE walblues_product_table SET available_qty = ? WHERE name = ?";
        DbConnection dbconnect = new DbConnection();
        Connection connect = dbconnect.connect();
        
        try {
        		Statement statement= connect.createStatement();
        		ResultSet rs= statement.executeQuery(selectsql);
        		PreparedStatement preparedstatement = connect.prepareStatement(sql);
        		while(rs.next())
    			{
        			System.out.println(rs.getString("name")+name);
        			
        			if( rs.getString("name").equals(name))
        			{
        		      System.out.println("into "+rs.getString("name")+name);
        			  int qty=Integer.parseInt(rs.getString("available_qty")); 
        			  int purchase_qty=Integer.parseInt(qnty);
        			  int inventory_qty=qty- purchase_qty;
        			  preparedstatement.setString(1, inventory_qty+"");
        			  preparedstatement.setString(2, name); 
        			  preparedstatement.executeUpdate();
        			}
        			
    			}
        	}
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, qnty);
            preparedStatement.executeUpdate();
            dbconnect.Disconnect(connect);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void add_product(String name, String price,String qty,String category)
    {
    	String sql = "INSERT INTO walblues_product_table (name, category, price, available_qty) VALUES (?,?,?,?)";
        DbConnection dbconnect = new DbConnection();
        Connection connect = dbconnect.connect();
        try {
            	PreparedStatement preparedStatement = connect.prepareStatement(sql);
            	preparedStatement.setString(1, name);
            	preparedStatement.setString(2, category);
            	preparedStatement.setString(3, price);
            	preparedStatement.setString(4, qty);
            	preparedStatement.executeUpdate();
            	dbconnect.Disconnect(connect);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void update_product(String name,String category,String price, String qty,String id)
    {
    	System.out.println(name+" "+category+" "+price+" "+qty+" "+id);
    	String sql = "UPDATE walblues_product_table SET name= ?,category=?, price=?, available_qty = ?  WHERE id = ?";
    	DbConnection dbconnect = new DbConnection();
        Connection connect = dbconnect.connect();
        try 
        {
        	PreparedStatement preparedstatement = connect.prepareStatement(sql);
        	 preparedstatement.setString(1,name);
			 preparedstatement.setString(2, category); 
			 preparedstatement.setString(3, price);
			 preparedstatement.setString(4, qty);
			 preparedstatement.setString(5,id);
			 preparedstatement.executeUpdate();
        	
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
    }
    
    public void delete_product(Integer id)
    {
    	String sql = "DELETE from walblues_product_table   WHERE id = ?";
    	DbConnection dbconnect = new DbConnection();
        Connection connect = dbconnect.connect();
        try 
        {
        	PreparedStatement preparedstatement = connect.prepareStatement(sql);
			 preparedstatement.setInt(1,id);
			 preparedstatement.executeUpdate();
        	System.out.println("executed.");
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
    }

    
}
