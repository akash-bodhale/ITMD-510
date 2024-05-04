package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import Model.ItemDao;
import Model.LoginDao;
import Model.SignUpDao;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	//global stage
	public static Stage stage;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			stage = primaryStage;
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
			Scene scene = new Scene(root,1710,1710);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
