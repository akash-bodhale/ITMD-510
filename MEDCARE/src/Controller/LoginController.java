package Controller;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import Model.LoginDao;

public class LoginController {
	
	@FXML
	private TextField usernameField;
	
	@FXML
    private PasswordField passwordField;
	
	public void handleLoginButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        preValidation(username,password);
    }

	private void preValidation(String username, String password) {
		if(username==null || username.trim().equals("")) 
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("username cannot be empty or have spaces");
			alert.showAndWait();
			return;
		}
		
		else if(password==null || password.trim().equals(""))
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("password cannot be empty or have spaces");
			alert.showAndWait();
			return;
		}
		
		else 
		{
			LoginDao login_dao=new LoginDao();
			String result = login_dao.postValidation(username, password);
			System.out.println(""+result);
			if(result.equals("User name and password not matched.try again!!")) 
			{
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText(result);
				alert.showAndWait();
			}
			else 
			{
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Welcome");
				alert.setHeaderText(null);
				alert.setContentText(result);
				alert.showAndWait();
				if(result.contains("Welcome admin"))
					AdminLogIn();
				else
					CustomerLogin();
			}
		}
		
	}	
	
	@FXML
	public void handleSignUpButton() {
        try {
           
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signup.fxml"));
        		Parent signUpRoot = loader.load();
        		
        		Scene signUpScene = new Scene(signUpRoot);

        		Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        		primaryStage.setScene(signUpScene);
        		primaryStage.setTitle("Sign Up");
        		primaryStage.show();
           }
           catch (Exception e) 
           {
             e.printStackTrace();
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText(null);
             alert.setContentText("Failed to load sign-up view!");
             alert.showAndWait();
           }
    }
	
	@FXML
	public void AdminLogIn()
	{
		System.out.println("admin login");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Admin.fxml"));
		try 
		{
			Parent AdminLoginIn = loader.load();
			Scene AdminLogin = new Scene(AdminLoginIn);
			
			
			
			Stage primaryStage = (Stage) usernameField.getScene().getWindow();
			primaryStage.setScene(AdminLogin);
    		primaryStage.setTitle("Admin Page");
    		primaryStage.show();
			
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void CustomerLogin()
	{
		System.out.println("customer login");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Customer.fxml"));
		try 
		{
			Parent CustomerLoginIn = loader.load();
			Scene CustomerLogin = new Scene(CustomerLoginIn);
			String username = usernameField.getText();
			
			CustomerController customerController = loader.getController();
	        
	        // Initialize the controller
	        customerController.initialize(username);
	        
	        customerController.loadData();
			
			Stage primaryStage = (Stage) usernameField.getScene().getWindow();
			primaryStage.setScene(CustomerLogin);
    		primaryStage.setTitle("Customer Page");
    		primaryStage.show();
			
			
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}

