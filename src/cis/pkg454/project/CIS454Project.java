/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.derby.jdbc.EmbeddedDriver;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Acts as the Server between the front end and back end
 * Launches GUI
 * @author OliviaFlynn
 */
public class CIS454Project extends Application {
    public static User currentUser = new User("joe98", "joe@joe.com", "", 100.0, true);
    
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
     * Checks if the username is already used by another user (returns false) or not (returns true)
     * @param username
     * @return 
     */
    static public boolean usernameAvailable(String username) {
        //Check in the database
        return true;
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
     * Creates a new user entry in the database
     * @param newUser 
     */
    static public void registerUser(User newUser) {
        // Set the user to the current user
        currentUser = newUser;
        
        // Add the new user to the database
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
     * Modifies the specified item in the user's selling list and in the database of books being sold
     * @param updatedBook 
     */
    static public void modifyItem(Textbook updatedBook) {
        // Modify the item in the user's selling list by getting the seller User object from the 
        // SellerID member of Textbook, and getting their selling list
        
        // Modify the item in the database of items being sold
    }
    
    /**
     * Deletes the specified item from the user's selling list and from the database of books being sold
     * @param updatedBook 
     */
    static public void deleteItem(Textbook textbook) {
        // Delete the item from the user's selling list by getting the seller User object from the 
        // SellerID member of Textbook, and getting their selling list
        
        // Delete the item in the database of items being sold
    }
    
    /**
     * Adds the specified report to the database
     * @param report 
     */
    static public void addReport(Report report) {
        // Add the item to the database of reports
    }
    
    /**
     * Updates a report item, used for resolving reports or adding comments
     * @param report 
     */
    static public void updateReport(Report report) {
        // Change the report's status to resolved
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
     * @throws java.sql.SQLException
     */
    public static Connection makeConnection() throws SQLException {
        Driver derbyEmbeddedDriver = new EmbeddedDriver();
        DriverManager.registerDriver(derbyEmbeddedDriver);
        Connection connection = DriverManager.getConnection("jdbc:derby:CIS454Database;create=true");
        return connection;
    }
    
    public static void createTable() throws SQLException {
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        DatabaseMetaData dbm = connection.getMetaData();
        // check if "UserTable" table is there and drop it
        ResultSet tables = dbm.getTables(null, null, "USERTABLE", new String[] {"TABLE"});
        if (tables.next()) statement.execute("drop table USERTABLE");
        // create and insert record to UserTable
        String query = "create table UserTable (userID integer not null, name varchar(30), username varchar(30) not null, password varchar(30) not null, address varchar(50), balance double default 0.0, isAdmin boolean default false, email varchar(30) not null, phoneNumber varchar(10), primary key (userID))";
        statement.executeUpdate(query);
        query = "INSERT into UserTable values (1, 'Default', 'default', 'default', '', 0.0, true, 'default@gmail.com', '3151234567')";
        statement.executeUpdate(query);
        query = "INSERT into UserTable values (2, 'Dummy', 'dummy', 'dummy', '', 0.0, false, 'dummy@gmail.com', '3159875643')";
        statement.executeUpdate(query);
        
        // check if "Textbook" table is there and drop it
        tables = dbm.getTables(null, null, "TEXTBOOKTABLE", new String[] {"TABLE"});
        if (tables.next()) statement.execute("drop table TEXTBOOKTABLE");
        // create Textbook table
        query = "create table TextbookTable (textbookID integer not null, name varchar(50) not null, price double not null, author varchar(50), isbn varchar(13), seller integer not null, primary key (textbookID), foreign key (seller) references USERS(userID))";
        statement.executeUpdate(query);
        query = "INSERT into TextbookTable (textbookID, name, price, author, seller) values (1, 'Hello World', 10.00, 'CIS454', 2)";
        statement.executeUpdate(query);
        
        // check if "Report" table is there and drop it
        tables = dbm.getTables(null, null, "REPORT", new String[] {"TABLE"});
        if (tables.next()) statement.execute("drop table REPORT");
        // create ""Report" table
        query = "create table Report (reportID integer not null, userID integer not null, type varchar(25) not null, status boolean, description varchar(500), comment varchar(500), primary key (reportID), foreign key (userID) REFERENCES USERS(userID))";
        statement.executeUpdate(query);
        query = "INSERT into Report values (1, 1, 'issue', false, 'test', 'debug')";
        statement.executeUpdate(query);
        
        // check if "Payment" tale is there and drop it
        tables = dbm.getTables(null, null, "PAYMENT", new String[] {"TABLE"});
        if (tables.next()) statement.execute("drop table PAYMENT");
        // create "Payment" table
        query = "create table Payment (paymentID integer not null, textbookID integer not null, sellerID integer not null, buyerID integer, cardNum varchar(16), cardSecuCode varchar(3), cardExp varchar(4), accNum varchar(12), routingNum varchar(9), method boolean, primary key (paymentID), foreign key (textbookID) references TEXTBOOK(textbookID), foreign key (sellerID) references USERS(userID), foreign key (buyerID) references USERS(userID))";
        statement.executeUpdate(query);
        query = "INSERT into Payment (paymentID, textbookID, sellerID, buyerID, cardNum, cardSecuCode, cardExp) values (1, 1, 1, 2, '1234123412341234', '123', '1234')";
        statement.executeUpdate(query);
        
    }
    
    public static int maxUserID() throws SQLException {
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT MAX(userID) AS maxUserID FROM UserTable";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt("maxUserID");
    }
    
    public static Boolean uniqueUsername(String username) throws SQLException {
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM UserTable WHERE username = '"+username+"'";
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) return false;
        return true;
    }
    
    public static void main(String[] args) throws SQLException {
        createTable();
        launch(args);
    }
    
}
