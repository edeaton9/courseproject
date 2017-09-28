
package storemanager;

import java.util.Scanner;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author ericdeaton
 */

public class StoreManager {

    /**
     * @param args the command line arguments
     */
    
    private static Vector<Product> productQueue = new Vector<Product>();
    
    public static void main(String[] args) {
        
        createProductSet(); // Creates Sample Data
        
        int opt;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inventory System Menu\n"
            + "1 - Checkout Customer\n"
            + "2 - Manage Inventory\n"
            + "3 - Quit");
        do
        {
         System.out.print("Enter code [1, 2, or 3]: ");
         opt = scan.nextInt();
         
         while (opt < 1 || opt > 3) {
             System.out.println("Invlaid option");
             System.out.print("Enter code [1, 2, or 3]: ");
            opt = scan.nextInt();
         }
         
         /*
         * Main menu switch. Choices: Checkout Customer, Manage Inventory
         */
         switch(opt)
         {
            case 1: // Checkout Customer 
               System.out.println("Checkout Customer ");
               Order currentOrder = new Order();
  
                do {
            
                    System.out.println("Checkout\n"
                         + "1 - Add Product\n"
                         + "2 - View Products\n"                  
                         + "3 - Pay\n");
                    System.out.println("Enter option 1, 2 or 3: ");
                    opt = scan.nextInt();
        
                     while (opt < 1 || opt > 3) {
                          System.out.println("Invlaid option");
                          System.out.println("Checkout\n"
                               + "1 - Add Product\n"
                               + "2 - View Products\n"
                               + "2 - Pay\n");
                          System.out.println("Enter option 1, 2 or 3: ");
                          opt = scan.nextInt();
                        }
        
                     /*
                     * Add Product to Order Switch
                     * Choices: Add Product, Pay with Cash
                     * Should direct user back to main menu after printing reciept
                     */
                    switch(opt) { 
            
                     case 1: //Add Product to Order
                        int productID; 
                        int quantity;
        
                        System.out.println("Enter the Product ID: ");
                        productID = scan.nextInt();
                        
                        // Searches to see if Product exist in ProductQueue
                        while (searchProductID(productID) == -1){
                        System.out.println("Unable to locate the product\n");
                        System.out.println("Enter the Product ID: ");
                        productID = scan.nextInt();
                        }
                
                       productID = searchProductID(productID); //productID becomes the index for product in productQueue
        
                       System.out.println("Enter the Quantity: ");
                       quantity = scan.nextInt();
                       
                       // Check to see if there is enough Product's quntity to supply order 
                       
                       while (productQueue.get(productID).getQuantity() < quantity) {
                           System.out.println("Not enough qunatity left to fill the order\n"
                                   + "There are only " + productQueue.get(productID).getQuantity() + " in stock" 
                                   + "Enter the Quantity: ");
                           quantity = scan.nextInt();
                       }
                       
                       // Remove Product Quantities from productQuene                     
                       int currentQuantity = productQueue.get(productID).getQuantity();
                       int newQuantity = currentQuantity - quantity;
                       productQueue.get(productID).setQuantity(newQuantity);
                                         
                       //Adds product to the order 
                       currentOrder.addProduct(productQueue.get(productID), quantity);
                       
                       
                       break;
                     case 2:
                         printProducts();
                        
                         break;
                     case 3: //Pay with Cash
                       double moneyPaid;
                       double totalPrice;
                       double change;
                
                       System.out.println("Enter the cash payment: $");
                       moneyPaid = scan.nextDouble();
                
                       totalPrice = currentOrder.getTotalPrice();
                
                       change = moneyPaid - totalPrice;
                
                       System.out.println("Total Price: " + totalPrice 
                           + "Money Recieved: " + moneyPaid
                         + "Change Due: " + change + "\n");
                       
                       break;
                    default:
                        break;
                      }
                 } while(opt > 0 && opt < 3);
                
               displayMainMenu();
               System.out.print("Enter code [1, 2, or 3]: ");
               opt = scan.nextInt(); 
               
               
               if (opt < 1 && opt > 3) {
                    System.out.println("Invlaid option");
                    System.out.print("Enter code [1, 2, or 3]: ");
                    opt = scan.nextInt();
                }
               
               break;
            case 2: // Display Manage Inventory Menu
               displayManagementMenu();
               do
               {
                 System.out.print("Enter code [1, 2, or 3]: ");
                 opt = scan.nextInt();
         
                 if (opt < 1 && opt > 3) {
                    System.out.println("Invlaid option");
                    System.out.print("Enter code [1, 2, or 3]: ");
                    opt = scan.nextInt();
                 }
                 
                 /*
                 * Manage Product Switch; 
                 * Choices: Add Product, Manage Product & Main Menu
                 */
                 switch(opt)
                 {
                    case 1: // Add Product to productQueue
                      
                        addProductToQueue();
                        
                        displayManagementMenu();
                        System.out.print("Enter code [1, 2, or 3]: ");
                        opt = scan.nextInt();
                        break;
                    case 2: // Manage Product
                        
                        manageProducts();
                        
                            
                        displayManagementMenu();
                        System.out.print("Enter code [1, 2, or 3]: ");
                        opt = scan.nextInt();
                        break;
                    default: //Main menu
                        break;
                 }
               } while (opt != 3); 
               
               displayMainMenu();
               System.out.print("Enter code [1, 2, or 3]: ");
               opt = scan.nextInt(); 
               
               if (opt < 1 && opt > 3) {
                    System.out.println("Invlaid option");
                    System.out.print("Enter code [1, 2, or 3]: ");
                    opt = scan.nextInt();
                }
               
               break;
            case 3: // Quit Application
               break;
            default:
         }
      } while (opt != 3); 
        
    }
    
    // Display for the Main Menu
    public static void displayMainMenu() {
        System.out.println("Inventory System Menu\n"
            + "1 - Checkout Customer\n"
            + "2 - Manage Inventory\n"
            + "3 - Quit");
    }
    
    // Display for the Management Menu
    public static void displayManagementMenu(){
        System.out.println("Item Management\n"
            + "1 - Add Product\n"
            + "2 - Manage Product\n"
            + "3 - Main Menu");
    }
    
     /*
     * Searches to see if product exist in productQueue
     * @param productIDin product primary key
     * @return index of product in productQueue
     */
    
     public static int searchProductID(int productIDin) {
        int index = -1;
        for(int i = 0; i < productQueue.size(); i++)
        {
            if (productQueue.get(i).getProductID() == productIDin)
            {
                 index = i;
            }   
        }
        return index; 
    }
     
     public static void printProducts(){
        if (productQueue.isEmpty())
        {
            System.out.println("There are no products in the Inventory");
        }
        else {
            for (int i = 0; i < productQueue.size(); i++) {
                System.out.println(productQueue.get(i).printProduct());
            }
        }
     }
     
     // Adds a new product to the product Queue
    public static void addProductToQueue() {
        Scanner scan = new Scanner(System.in);
        
        String product_name;
        String description;
        double price;
        double taxrate;       
        int quantity;
        int experation_date;
        System.out.println("Add Item to Inventory\n");
        
        System.out.println("Enter the product's name: ");
        product_name = scan.nextLine();
        
        System.out.println("Enter the product's description: ");
        description = scan.nextLine();
        
        System.out.println("Enter the product's price: ");
        price = scan.nextDouble();
        
        System.out.println("Enter the product's tax rate: ");
        taxrate = scan.nextDouble();
        
        System.out.println("Enter the product's quantity: ");
        quantity = scan.nextInt();
        
        System.out.println("Enter the product's experation date: ");
        experation_date = scan.nextInt();
        
        Product product = new Product(product_name, description, price , taxrate, quantity, experation_date);
         
        productQueue.add(product);
    }
    
    /*
    * Display Menu for User to select main attributes to modify 
    */
    public static void displayProductAttributesMenu()
    {
        System.out.println("1 - Product Name\n"
                + "2 - Product Description\n"
                + "3 - Product Price\n"
                + "4 - Product Taxrate\n"
                + "5 - Product Quantity\n"
                + "6 - Product Experation Date\n"
                + "7 - Return to Management Menu\n");
    }
    
    public static void manageProducts() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < productQueue.size(); i++) {
            productQueue.get(i).printProduct();
        }
        System.out.println("\nEnter the product ID for the product you would like to modify: ");
        int productID = scan.nextInt();
        
        // Searches to see if Product exist in ProductQueue
        while (searchProductID(productID) == -1){
             System.out.println("Unable to locate the product\n");
             System.out.println("Enter the Product ID: ");
             productID = scan.nextInt();
        }
        
        int productIndex = searchProductID(productID);
        
        System.out.println("Which field would you like to modify?");
        displayProductAttributesMenu();
        System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
        int opt = scan.nextInt();
        while(opt < 1 || opt > 7) {
            System.out.println("Invalid Option, please try again");
            System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
            opt = scan.nextInt();
        }
        
        /*
        * Switch for selecting Product attribute to modify 
        */
        switch(opt) {
            case 1:     // Modify Product Name
                System.out.println("The Current Prodcut Name: " + productQueue.get(productIndex).getProductName());
                System.out.println("Enter the new Product Name: ");
                String new_product_name = scan.nextLine();
                productQueue.get(productIndex).setProductName(new_product_name);
                
                System.out.println("Which field would you like to modify?");
                displayProductAttributesMenu();
                System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                opt = scan.nextInt();
                while(opt < 1 || opt > 7) {
                    System.out.println("Invalid Option, please try again");
                    System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                        opt = scan.nextInt();
                 }
                
                break;
            case 2:     // Modify Product Description     
                System.out.println("The Current Description: " + productQueue.get(productIndex).getDescription());
                System.out.println("Enter the new Description: ");
                String new_product_description = scan.nextLine();
                productQueue.get(productIndex).setDescription(new_product_description);
                
                System.out.println("Which field would you like to modify?");
                displayProductAttributesMenu();
                System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                opt = scan.nextInt();
                while(opt < 1 || opt > 7) {
                    System.out.println("Invalid Option, please try again");
                    System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                        opt = scan.nextInt();
                 }
                
                break;
            case 3:     // Modify Product's Price
                System.out.println("The Current Prodcut Price: " + productQueue.get(productIndex).getPrice());
                System.out.println("Enter the new Product Name: ");
                Double new_product_price = scan.nextDouble();
                productQueue.get(productIndex).setPrice(new_product_price);
                
                System.out.println("Which field would you like to modify?");
                displayProductAttributesMenu();
                System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                opt = scan.nextInt();
                while(opt < 1 || opt > 7) {
                    System.out.println("Invalid Option, please try again");
                    System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                        opt = scan.nextInt();
                 }
                
                break;
            case 4:     // Modify Product taxrate
                System.out.println("The Current Prodcut Taxrate: " + productQueue.get(productIndex).getTaxRate());
                System.out.println("Enter the new Product Taxrate: ");
                Double new_product_taxrate = scan.nextDouble();
                productQueue.get(productIndex).setTaxRate(new_product_taxrate);
                
                System.out.println("Which field would you like to modify?");
                displayProductAttributesMenu();
                System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                opt = scan.nextInt();
                while(opt < 1 || opt > 7) {
                    System.out.println("Invalid Option, please try again");
                    System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                        opt = scan.nextInt();
                 }
                
                break; 
            case 5:     // Modify Product Quantity
                System.out.println("The Current Prodcut Quantity: " + productQueue.get(productIndex).getQuantity());
                System.out.println("Enter the new Product Quatity: ");
                int new_product_quantity = scan.nextInt();
                productQueue.get(productIndex).setQuantity(new_product_quantity);
                
                System.out.println("Which field would you like to modify?");
                displayProductAttributesMenu();
                System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                opt = scan.nextInt();
                while(opt < 1 || opt > 7) {
                    System.out.println("Invalid Option, please try again");
                    System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                        opt = scan.nextInt();
                 }
                
                break;
            case 6:     // Modify Product Experation Date 
                System.out.println("The Current Prodcut Experation Date: " + productQueue.get(productIndex).getExperationDate());
                System.out.println("Enter the new Product Experation Date: ");
                int new_product_exp = scan.nextInt();
                productQueue.get(productIndex).setExperationDate(new_product_exp);
                
                System.out.println("Which field would you like to modify?");
                displayProductAttributesMenu();
                System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                opt = scan.nextInt();
                while(opt < 1 || opt > 7) {
                    System.out.println("Invalid Option, please try again");
                    System.out.println("Enter an option [1,2,3,4,5,6,7]: ");
                        opt = scan.nextInt();
                 }
                
                break;
            default:
                break;
          }
         
        
    }
    
    public static void createProductSet(){
        // Creating Sample Products
        
        Product product1 = new Product("Apple", "Red Delicious", 1.75, 0.06, 50, 101317);
        Product product2 = new Product("Bananna", "Constains 10 Banannas", 2.00, 0.06, 30, 101517);
        Product product3 = (new Product("Eggs", "Contains 12 eggs", 2.75, 0.08, 40, 101717));
        Product product4 = (new Product("Milk", "Whole Milk", 2.50, 0.08, 70, 100517));
        Product product5 = (new Product("Bread", "Contains 20 slices", 3.00, 0.06, 45, 100617));
        Product product6 = (new Product("Cheese", "Shredded Cheddar", 4.00, 0.08, 60, 112717));
        Product product7 = (new Product("Steak", "Contains 2 T-Bones", 10.00, 0.05, 35, 100417));
        
        
        productQueue.add(product1);
        productQueue.add(product2);
        productQueue.add(product3);
        productQueue.add(product4);
        productQueue.add(product5);
        productQueue.add(product6);
        productQueue.add(product7);
        
    }
    
   
}
