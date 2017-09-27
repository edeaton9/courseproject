/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanager;

/**
 *
 * @author ericdeaton
 */
public class User {
    private String user_name;
    private int userID; // Primary key for each User
    
    
    /*
    * Default Constuctor
    */
    User(){
        
    }
    
    /*
    * @param user_name
    * @param userID 
    */
    User(String user_name, int userID) {
        this.user_name = user_name;
        this.userID = userID;
        
    }
    
    /*
    * @param user_name
    */
    public void setUserName(String user_name) {
        this.user_name = user_name;
    }
    
    /*
    * @return user_name
    */
    public String getUserName() {
        return this.user_name;
    }
    
    /*
    * @param userID
    */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    /*
    * @return userID
    */
    public int getUserID() {
        return this.userID;
    }
    
    
}