/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Acts as the Server between the front end and back end
 * Launches GUI
 * @author OliviaFlynn
 */
public class CIS454Project extends Application {
    static public User currentUser = new User("joe98", "joe@joe.com", "", 1.0, false);
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Sell or Buy Textbooks Here!!!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * Checks the provided credentials against users in the database.
     * Sets the current user to the one whose credentials have been provided if valid and returns true
     * Returns false if there are invalid credentials
     * @param username
     * @param password
     * @return 
     */
    static public boolean login(String username, String password) {
        // Check if username/password combo exists in database, if not then return false
        // If the combination exists, get the User object from the database
        
        // Set currentUser to the user if the credentials are valid
        
        return true;
    }
    
    /**
     * Since the user is logging out, it is no longer the current user
     */
    static public void logout() {
        currentUser = null;
    }
    
    /**
     * Updates the correct user object in the database
     * Sets the current user to the new user object with updated information
     * @param newUser 
     */
    static public void updateUser(User newUser) {
        currentUser = newUser;
        // Update backend with new user object
    }
    
    /**
     * Logs the user out of the application and deletes their record from the database
     * @param user 
     */
    static public void deleteAccount(User user) {
        // Log the user out of the application
        logout();
        
        // Delete user from database, and all textbooks sold by this user
    }
    
    /**
     * Adds the specified item to the user's selling list and adds to the database of books being sold
     * @param item 
     */
    static public void addItem(Textbook item) {
        // Add the item to the user's selling list
        currentUser.getSellingBooks().add(item);
        
        // Add the item to the database of items being sold
    }
    
    /**
     * Adds the specified report to the database
     * @param report 
     */
    static public void addReport(Report report) {
        // Add the item to the database of reports
    }

    /**
     * Put payment records in the payment database
     * Remove textbooks corresponding to those that were just bought from textbook database
     * Remove textbooks from selling list of sellers
     * Subtract the price from the user's balance
     * Clear the user's shopping cart
     * @param payments 
     */
    static public void submitPayments(ArrayList<Payment> payments, double price) {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    
}
