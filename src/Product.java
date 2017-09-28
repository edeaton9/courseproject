
package storemanager;

/**
 * Handles the individual products in the inventory 
 * 
 * @author ericdeaton
 */
public class Product{
    private String product_name;
    private String description;
    private double price;
    private double taxrate;
    private int productID;
    private int quantity;
    private int experation_date;
    private static int productCount = 1;
    
    Product() {
        productID = productCount;
        productCount++;
    }
    
    /*
    * @param product_name 
    * @param description 
    * @param price 
    * @param taxrate
    * @param productID 
    * @param quantity
    * @param experation_date
    */
    
    Product(String product_name, String description, double price, double taxrate, int quantity, int experation_date) {
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.taxrate = taxrate;
        this.productID = productCount;
        productCount++;
        this.quantity = quantity;
        this.experation_date = experation_date;
    }
    
    /*
    * @param product_name 
    */
    public void setProductName(String product_name) {
        this.product_name = product_name;
    }
    
    /*
    * @return product_name
    */
    public String getProductName() {
        return this.product_name;
    }
    
    /*
    * @param description
    */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /*
    * @return description
    */
    public String getDescription() {
        return this.description;
    }
    
    /*
    * @param price 
    */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /*
    * @return price
    */
    public double getPrice() {
        return this.price;
    }
    
    /*
    * @param taxrate 
    */
    public void setTaxRate(double taxrate) {
        this.taxrate = taxrate;
    }
    
    /*
    * @return taxrate
    */
    public double getTaxRate() {
        return this.taxrate;
    }
    
    /*
    * @param producID
    */
    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    
    /*
    * @return productID
    */
    public int getProductID() {
        return this.productID;
    }
    
    /*
    * @param quantity 
    */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /*
    * @return quantity
    */
    public int getQuantity() {
        return this.quantity;
    }
   
    
    
    /*
    * @param experation_date
    */
    public void setExperationDate(int experation_date) {
        this.experation_date = experation_date;
    }
    
    /*
    * @return experation_date
    */
    public int getExperationDate() {
        return this.experation_date;
    }
    
    public String printProduct() {
        String output = "";
        output += "Product Name: " + product_name + "\t";
        output += "Description: " + description + "\t";
        output += "Tax Rate: " + taxrate + "\t";
        output += "Product ID: " + productID + "\t";
        output += "Quantity: " + quantity + "\t";
        output += "Experation Date: " + experation_date + "\t";
        return output;
    }
    
    
}
