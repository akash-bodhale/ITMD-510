package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Model.ItemDao;
import Model.Product;
import Model.Product_cart;

public class CustomerController {

	@FXML
	private Button log_out;

	@FXML
	private ComboBox<String> product;

	@FXML
	private TextField product_quantity;

	@FXML
	private TableView<Product> product_table;
	
	@FXML
	private TableView<Product_cart>cart;

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
	private TableColumn<Product_cart, String> Product_Name;
	
	@FXML
	private TableColumn<Product_cart,String>Product_Qty;
	
	@FXML
	private TableColumn<Product_cart,String>Prc;

	@FXML
	private Button add_to_cart;

	@FXML
	private Button calculate_price;

	@FXML
	private Label total_lbl;

	@FXML
	private Button reset_btn;
	
	private ObservableList<Product_cart> cartItems = FXCollections.observableArrayList();
	
	private ArrayList<Double> totalPrice = new ArrayList<Double>();
	
	@FXML
	private Label customerName;

	@FXML
	public void initialize(String customerNm) {
		ItemDao itemDao = new ItemDao(); 
		String[] customerNme = customerNm.split(" ");
		customerName.setText(customerNme[0]);

		
		product_id.setCellValueFactory(new PropertyValueFactory<>("productID"));
		product_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
		product_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		product_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		product_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		Product_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
		Product_Qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		Prc.setCellValueFactory(new PropertyValueFactory<>("prc"));

		// Retrieve data from the database and populate the TableView
		ObservableList<Product> productList = itemDao.retriveItem();
		product_table.setItems(productList);

		ObservableList<String> productNames = FXCollections.observableArrayList();
		ObservableList<String> productQuantity = FXCollections.observableArrayList();
		for (Product product : productList) {
			productNames.add(product.getProductName());
			productQuantity.add(product.getQuantity());
		}

		product.setItems(productNames);

	}

	public void loadData() {
		ItemDao itemDao = new ItemDao(); // Initialize ItemDao

		// Set up cell value factories for each column
		product_id.setCellValueFactory(new PropertyValueFactory<>("productID"));
		product_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
		product_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		product_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		product_category.setCellValueFactory(new PropertyValueFactory<>("category"));

		// Retrieve data from the database and populate the TableView
		ObservableList<Product> productList = itemDao.retriveItem();
		product_table.setItems(productList);
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

	public void CalculatePrice() {
		System.out.println("hello for calculate price");
		String quantityText = product_quantity.getText();
		ItemDao itemDao = new ItemDao();
		ObservableList<Product> productList = itemDao.retriveItem();
		if (quantityText != null && !quantityText.isEmpty()) {
			int Quantity = Integer.parseInt(quantityText);
			double price = 0;
			String productName = product.getSelectionModel().getSelectedItem();
			System.out.println(productList);
			
			for (Product l : productList) {
				if (productName.equals(l.getProductName())) {
					price = Double.parseDouble(l.getPrice());
					price=Double.parseDouble(l.getPrice());
					break;
				}
			}

			double sum = price * Quantity;
			total_lbl.setText(String.valueOf(sum));
		} else {
			System.out.println("Product quantity is empty");
		}
	}

	public void ResetOrder() {
		product.getSelectionModel().clearSelection();
		product_quantity.setText("");
		total_lbl.setText("$0.0");
		cart.setItems(null);
	
	}
	

	
	
	public void AddToCart() {
	    System.out.println("Add To Cart" + " - " + product.getSelectionModel().getSelectedItem() + " - " + product_quantity.getText());
	    String productName = product.getSelectionModel().getSelectedItem();
	    String quantityText = product_quantity.getText();
	    double qt=Integer.parseInt(quantityText);
	    double pc=0;
	    ItemDao itemDao = new ItemDao();
		ObservableList<Product> productList = itemDao.retriveItem();
		for (Product l : productList) {
			if (productName.equals(l.getProductName())) {
				pc = Double.parseDouble(l.getPrice());
				
				break;
			}
		}
	    double sum = pc * qt;
	    totalPrice.add(sum);
	    String prc=""+sum;
	    
	    // Create a new Product object with the selected item and quantity
	    Product_cart productToAdd = new Product_cart(productName, quantityText,prc);
	    
	    System.out.println(productToAdd);
	    // Add the product to the cartItems list
	    cartItems.add(productToAdd);
	    System.out.println(cartItems);
	    
	    // Update the TableView with the updated cartItems list
	    cart.setItems(cartItems);
	}
	
	public  void payment()
	{
		double Total_Amt=0;
		if(!totalPrice.isEmpty() &&  !cartItems.isEmpty())
		{
			
			for(double a :  totalPrice)
			{
				Total_Amt+=a;
			}
			for(Product_cart a: cartItems)
			{
				ItemDao itemDao = new ItemDao();
				itemDao.Insert_order(a.getName(), a.getQuantity(), a.getPrc(), customerName.getText());
				
				itemDao.Update_stock(a.getName(), a.getQuantity());
			}
			Alert alert=new Alert(AlertType.CONFIRMATION);
			alert.setTitle("PAYMENT");
			alert.setHeaderText("Pymt");
			alert.setContentText("Your total payment is"+Total_Amt);
			alert.showAndWait();
			
			return;
		}
	}
}





