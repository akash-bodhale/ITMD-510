package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Model.ItemDao;
import Model.Product;

public class AdminController {
	
	@FXML
	private TableView<Product> product_table;
	
	@FXML
	private TableColumn<Product, String> product_id;
	
	@FXML
	private TableColumn<Product, String> product_name;
	
	@FXML
	private TableColumn<Product, String> product_price;
	
	@FXML
	private TableColumn<Product, String> product_qty;
	
	@FXML
	private TableColumn<Product, String> product_category;
	
	@FXML
	private Button log_out;
	
	@FXML
	private TextField product_name_add;
	
	@FXML
	private TextField product_price_add;
	
	@FXML
	private TextField product_quantity_add;
	
	@FXML
	private TextField product_category_add;
	
	
	@FXML
	private TextField product_name_update ;
	
	@FXML
	private TextField product_price_update;
	
	@FXML
	private TextField product_quantity_update;
	
	@FXML
	private TextField product_category_update;
	
	@FXML
	private Button add_product;
	
	@FXML
	private Button update_product;
	
	@FXML
	private TextField product_name_id ;
	
	@FXML
	private ComboBox<String> product_id_update;
	
	@FXML
	private ComboBox<String>  product_id_delete;
	
	@FXML
	public void initialize() {
		
		ItemDao itemDao = new ItemDao();
		product_id.setCellValueFactory(new PropertyValueFactory<>("productID"));
		product_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
		product_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		product_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		product_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		
		ObservableList<Product> productList = itemDao.retriveItem();
		
		ArrayList<String> id = new ArrayList();
		
		ObservableList<Product> products = itemDao.retriveItem();
		for (Product product : products) {
			id.add(product.getProductID());
			System.out.println(product.getProductID());
		}
		
		product_id_update.getItems().setAll(id);
		
		product_id_delete.getItems().setAll(id);
		
		product_table.setItems(productList);
	}
	
	public void addProduct()
	{
		String product_name=product_name_add.getText();
		
		String product_price=product_price_add.getText();
		
		String product_quantity=product_quantity_add.getText();
		
		String product_category=product_category_add.getText();
		
		ItemDao itemDao = new ItemDao();
		
		itemDao.add_product(product_name,product_price,product_quantity,product_category);
		
		ObservableList<Product> productList = itemDao.retriveItem();
		
		product_table.setItems(productList);
		
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Product added");
		alert.setHeaderText("product");
		alert.setContentText("product "+product_name+" is sucessfully added");
		alert.showAndWait();
		
		
	}
	
	public void updateProduct()
	{
		System.out.println("update product.");
		
		ItemDao itemDao = new ItemDao();
		
		
		
		String product_name=product_name_update.getText();
		
		String product_price=product_price_update.getText();
		
		String product_quantity=product_quantity_update.getText();
		
		String product_category=product_category_update.getText();
		
		
		String selectedItem = product_id_update.getSelectionModel().getSelectedItem();
		
		itemDao.update_product( product_name, product_category, product_price,  product_quantity, selectedItem);
		
		ObservableList<Product> productList = itemDao.retriveItem();
		
		product_table.setItems(productList);
		
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Product updated.");
		alert.setHeaderText("product");
		alert.setContentText("product "+product_name+" is sucessfully updated");
		alert.showAndWait();
	}
	
	public void deleteProduct()
	{
		System.out.println("delete product.");
		ItemDao itemDao = new ItemDao();
		Integer selectedItem = Integer.parseInt(product_id_delete.getSelectionModel().getSelectedItem());
		
		System.out.println(selectedItem);
		
		itemDao.delete_product(selectedItem);
		
		ObservableList<Product> productList = itemDao.retriveItem();
		
		product_table.setItems(productList);
		
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Product deleted.");
		alert.setHeaderText("product");
		alert.setContentText("product "+selectedItem+" is sucessfully deleted.");
		alert.showAndWait();
		
	}
	
	
	
	public void logout() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("See You Soon!!");
		alert.setHeaderText("Thank you!!");

		// Add buttons to the alert dialog
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(yesButton, cancelButton);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == yesButton) {

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage primaryStage = (Stage) log_out.getScene().getWindow();

				primaryStage.setScene(scene);
				primaryStage.setTitle("Login Page");
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

