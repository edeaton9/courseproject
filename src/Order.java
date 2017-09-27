
package storemanager;

import java.util.Vector;


/**
 * Handles a Customer's order 
 * 
 * @author ericdeaton
 */
public class Order {
    private double total_price;
    private int orderID;
    private int time;
    private int date;
    private Vector<Product> products;
    private Vector productQunatity;
    private static int orderCount;
   
    
    
   /*
    * Default constructor
    */
   
    Order() {
        orderID = orderCount;
        total_price = 0;
        orderCount++;
    }
    
    /*
     * @param total_price total price inputted 
     * @param orderID the primary key for the order inputted
     * @param time the time of the order checkout inputted 
     * @param date the date of checkout inputted 
     * @param customer the current customer's info. 
     */
    
    Order(double total_price, int orderID, int time, int date) {
        this.total_price = total_price;
        this.orderID = orderID;
        this.time = time;
        this.date = date;
        
    }
    
    /*
    * @param total_price of order inputted 
    */
    public void setTotalPrice(double total_price){
        this.total_price = total_price;
    }
    
    /*
    * @return total_price of the current order 
    */
    public double getTotalPrice () {
        return this.total_price;
    }
    
    /*
    * @param orderID inputted 
    */
    public void setOrderID(int orderID) {
        this.orderID = orderID; 
    }
    
    /*
    * @return orderID for current order
    */
    public int getOrderID() {
        return this.orderID;
    }
    
    /*
    * @param time inputted 
    */
    public void setTime(int time) {
        this.time = time;
    }
    
    /*
    * @return time of checkout for the order 
    */
    public int getTime() {
        return this.time;
    }
    
    /*
    * @param date of order checkout inputted 
    */
    public void setDate(int date) {
        this.date = date;
    }
    
    /*
    * @return date of order checkout 
    */
    public int getDate() {
        return this.date;
    }
    
    public void addProduct(Product product, int quantity) {
        products.add(product);
        total_price = product.getPrice();
    }
    
    public void calculateTotal() {
        for(int i = 0; i < products.size(); i++) {
            this.total_price += (products.get(i).getPrice() * ((Integer)this.productQunatity.get(i)));
        }   
    }
    
    
    
    /*
    * prints the recipt of order 
    */
    public String printProducts(){
        String output = "";
        int cartSize = products.size();
        if (cartSize == 0) {
            output += "Cart is currently Empty";
        }
        else {
            for (int i = 0; i < cartSize; i++) {
                output += products.get(i).printProduct();
              }
             output += "\n";
        }
        return output;
    }
}
