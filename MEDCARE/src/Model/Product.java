package Model;

public class Product {
	
	private String ProductID;
	private String ProductName;
	private String Category;
	private String Price;
	private String AvailableQty;
	private String TotalPrice;
	
	public String getProductID() {
		return ProductID;
	}
	public String getProductName() {
		return ProductName;
	}
	public String getQuantity() {
		return AvailableQty;
	}

	public String getPrice() {
		return Price;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getCategory() 
	{
		return Category;
	}
	public String setTotalPrice() {
		return TotalPrice;
	}
	public void setProductID(String productID) {
		ProductID = productID;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public void setQuantity(String quantity) {
		AvailableQty = quantity;
	}
	public void setPrice(String price) {
		Price = price;
	}
	
	@Override
    public String toString() {
        return "{" +
                "productID='" + ProductID + '\'' +
                ", productName='" + ProductName + '\'' +
                ", category='" + Category + '\'' +
                ", price='" + Price + '\'' +
                ", quantity='" + AvailableQty + '\'' +
                '}';
    }

}
