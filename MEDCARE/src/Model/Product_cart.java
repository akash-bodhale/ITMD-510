package Model;

public class Product_cart {
    private String name;
    private String quantity;
    private String prc;

    public Product_cart(String name, String quantity, String price ) {
        this.name = name;
        this.quantity = quantity;
        this.prc=price;
    }

    // Getters and setters for name and quantity
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrc(String price) {
    	this.prc=price;
    }
    
    public String getPrc()
    {
    	return prc;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() 
    {
    	return "{" +
                "Name='" + name + '\'' +
                ", productQuantity='" + quantity + '\'' +
                ", price='" + prc + '\'' +
                '}';
    }
}
