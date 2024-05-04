package Controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import Model.SignUpDao;

public class SignUpController implements Initializable
{
	@FXML
	private ComboBox<String> User_type;
	
	@FXML
	private TextField User_name;
	
	@FXML
	private TextField User_password;
	
	@FXML
	private TextField Email_id;
	
	@FXML
	private ComboBox<String> Code;
	
	@FXML
	private TextField Phone_number;
	
	@FXML
	private TextField Delivery_Address;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		ObservableList<String> type = FXCollections.observableArrayList();
		ObservableList<String> contactCode = FXCollections.observableArrayList();
		type.add("admin");
		type.add("customer");
		contactCode.addAll("+91","+1");
		Code.getItems().addAll(contactCode);
		User_type.getItems().addAll(type);
		
	}
	
	public void createNewUser()
	{
		String result=signUpPreValidation(User_type.getValue(),User_name.getText(),User_password.getText(),Email_id.getText(),Phone_number.getText(),Delivery_Address.getText());
		if(result.equals("SUCESS"))
		{
			SignUpDao signup=new SignUpDao();
			String contact_number = Code.getValue()+"-"+Phone_number.getText();
			signup.createNewUser(User_type.getValue(),User_name.getText(),User_password.getText(),Email_id.getText(),contact_number,Delivery_Address.getText());
			
			ReturnLogInPage();
		}
		else
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Data not persisted some error in it.");
			alert.showAndWait();	
		}
		
		System.out.println("You are in sign up page");
	}
	
	public String signUpPreValidation(String user_type,String username,String password,String EmailId,String Phonenumber,String DeliveryAddress)
	{
		// System.out.println(user_name);
		if(username.isEmpty() || !username.matches("^(?=.*[A-Z]).+$"))
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("username cannot be empty or atleast contain 1 Captial letter");
			alert.showAndWait();
			return "ERROR";
			
		}
		if(username.isEmpty() ||!password.matches("^(?=.*[A-Z])(?=.*_)(?=.*@).+$"))

		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("password cannot be empty or contain atleast 1 Captial letter,1 @ and one _");
			alert.showAndWait();
			return "ERROR";
		}
		if(EmailId.isEmpty()||!EmailId.contains("@hawk.iit.edu"))
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Only hawk email accepted");
			alert.showAndWait();
			return "ERROR";
		}
		if(Phonenumber.isEmpty() || Phonenumber.length()==9)
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("wrong number 10 digit please!");
			alert.showAndWait();
			return "ERROR";
		}
		return "SUCESS";
	}
	
	@FXML
	public void ReturnLogInPage() {
        try {
            // Load the FXML file of the sign-up view
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
        		Parent loginRoot = loader.load();

        		// Create a new scene with the sign-up view
        		Scene loginScene = new Scene(loginRoot);

        		// Get the primary stage and set the sign-up scene
        		Stage primaryStage = (Stage) User_type.getScene().getWindow();
        		primaryStage.setScene(loginScene);
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
	
}

