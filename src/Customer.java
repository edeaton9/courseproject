/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanager;

import java.util.Vector;

/**
 *
 * @author ericdeaton
 */
public class Customer {
    private String customer_name;
    private int customerID; //Primary key for each Customer
    
    
    /*
    * Deafult Constructor 
    */
    Customer() {}
    
    /*
    * @param customer_name
    * @param customerID
    */
    Customer(String customer_name, int customerID){
        this.customer_name = customer_name;
        this.customerID = customerID;
    }
    
    /*
    * @param customer_name
    */
    public void setCustomerName(String customer_name){
        this.customer_name = customer_name;
    }
    
    /*
    * @return customer_name
    */
    public String getCustomerName() {
        return customer_name;
    }
    
    
}
