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
    public static User currentUser = new User("", "", "", 0.0, true);
    
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
    public static boolean login(String username, String password) throws SQLException  {
        // if username/password combo does not exist in database, return false
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT name, balance, isAdmin, email, phoneNumber, address, userID FROM UserTable WHERE username = '"+username+"' AND password = '"+password+"'";
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) return false;
        
        // get user info from backend
        String name = resultSet.getString("name");
        double balance = resultSet.getDouble("balance");
        boolean isAdmin = resultSet.getBoolean("isAdmin");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phoneNumber");
        String address = resultSet.getString("address");
        int userID = resultSet.getInt("userID");
        
        query = "SELECT * FROM Textbook WHERE seller = "+userID;
        resultSet = statement.executeQuery(query);
        ArrayList<Textbook> sell = new ArrayList<>();
        while (resultSet.next()) {
            sell.add(new Textbook(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getString("author"), resultSet.getString("isbn"), resultSet.getInt("textbookID"), resultSet.getInt("seller")));
        }
        
        query = "SELECT textbookID FROM ShoppingCart WHERE userID = "+userID;
        resultSet = statement.executeQuery(query);
        ArrayList<Integer> textbookIDlist = new ArrayList<>();
        while (resultSet.next()) {
            textbookIDlist.add(resultSet.getInt("textbookID"));
        }
        ArrayList<Textbook> shop = new ArrayList<>();
        for (int i = 0; i < textbookIDlist.size(); i++) {
            int textbook = textbookIDlist.get(i);
            query = "SELECT * FROM Textbook WHERE id = "+textbook;
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                shop.add(new Textbook(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getString("author"), resultSet.getString("isbn"), resultSet.getInt("textbookID"), resultSet.getInt("seller")));
            }
        }
    
        // update the user object
        currentUser.setName(name);
        currentUser.setBalance(balance);
        currentUser.setIsAdmin(isAdmin);
        currentUser.setUsername(username);
        currentUser.setPassword(password);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.setAddress(address);
        currentUser.setId(userID);
        currentUser.setSellingBooks(sell);
        currentUser.setShoppingCart(shop);
        
        return true;
    }
    
    /**
     * Since the user is logging out, it is no longer the current user
     */
    public static void logout() {
        currentUser.setId(0);
        currentUser.setName("");
        currentUser.setUsername("");
        currentUser.setEmail("");
        currentUser.setPassword("");
        currentUser.setAddress("");
        currentUser.setPhoneNumber("");
        currentUser.setBalance(0.0);
        currentUser.setShoppingCart(new ArrayList<>());
        currentUser.setSellingBooks(new ArrayList<>());
        currentUser.setIsAdmin(false); 
    }
    
    /**
     * Checks if the username is already used by another user (returns false) or not (returns true)
     * @param username
     * @return 
     * @throws java.sql.SQLException 
     */  
     public static boolean usernameAvailable(String username) throws SQLException {
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM UserTable WHERE username = '"+username+"'";
        ResultSet resultSet = statement.executeQuery(query);
        return !resultSet.next();
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
    static public void deleteAccount(User user) throws SQLException{
        // Delete user from database, and all textbooks sold by this user
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "DELETE FROM UserTable WHERE userID = "+CIS454Project.currentUser.getId();
        statement.executeUpdate(query);
        
        // Log the user out of the application
        logout();
    }
    
    /**
     * Adds the specified item to the user's selling list and adds to the database of books being sold
     * @param item 
     * @throws java.sql.SQLException 
     */
    static public void addItem(Textbook item) throws SQLException {
        // Add the item to the user's selling list
        ArrayList<Textbook> alist = currentUser.getSellingBooks();
        alist.add(item);
        currentUser.setSellingBooks(alist);
        
        // Add the item to Textbook table
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT into Textbook values ("+item.getId()+", '"+item.getName()+"', "+item.getPrice()+", '"+item.getAuthor()+"', '"+item.getIsbn()+"', "+item.getSeller()+")";
        statement.executeUpdate(query);
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
    static public void addReport(Report report) throws SQLException {
        // Add the item to the database of reports
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT into Report values ("+report.getId()+", "+report.getRepoteeId()+", '"+report.getType()+"', "+report.getStatus()+", '"+report.getDescription()+"', '')";
        statement.executeUpdate(query);
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
        // if "UserTable" is not there, create and insert record
        String query;
        ResultSet tables = dbm.getTables(null, null, "USERTABLE", new String[] {"TABLE"});
        if (!tables.next()) {
            //statement.execute("drop table USERTABLE");
            query = "create table UserTable (userID integer not null, name varchar(30), username varchar(30) not null, password varchar(30) not null, address varchar(50), balance double default 0.0, isAdmin boolean default false, email varchar(30) not null, phoneNumber varchar(10), primary key (userID))";
            statement.executeUpdate(query);
            query = "INSERT into UserTable values (1, 'Default', 'default', 'default', '', 0.0, true, 'default@gmail.com', '3151234567')";
            statement.executeUpdate(query);
            query = "INSERT into UserTable values (2, 'Dummy', 'dummy', 'dummy', '', 0.0, false, 'dummy@gmail.com', '3159875643')";
            statement.executeUpdate(query);
            query = "INSERT into UserTable values (3, 'Test', 'test', 'test', '', 0.0, false, 'test@gmail.com', '3150123456')";
            statement.executeUpdate(query);
        }        
        
        // if "Textbook" is not there, create it and insert record
        tables = dbm.getTables(null, null, "TEXTBOOK", new String[] {"TABLE"});
        if (!tables.next()) {
            query = "create table Textbook (textbookID integer not null, name varchar(50) not null, price double not null, author varchar(50) not null, isbn varchar(13), seller integer not null, primary key (textbookID), foreign key (seller) references USERTABLE(userID))";
            statement.executeUpdate(query);
            query = "INSERT into Textbook (textbookID, name, price, author, seller) values (1, 'Hello World', 10.00, 'CIS454', 2)";
            statement.executeUpdate(query);
        }
        
        // if "ShoppingCart" is not there, create it
        tables = dbm.getTables(null, null, "SHOPPINGCART", new String[] {"TABLE"});
        if (!tables.next()) {
            query = "CREATE TABLE ShoppingCart (userID int NOT NULL, textbookID int NOT NULL, PRIMARY KEY (userID, textbookID), FOREIGN KEY (userID) REFERENCES UserTable(userID), FOREIGN KEY (textbookID) REFERENCES Textbook(textbookID))";
            statement.executeUpdate(query);
            query = "INSERT into ShopppingCart (2, 1)";
            statement.executeUpdate(query);
        }      
        
        // if "Payment" is not there, create it and insert record
        tables = dbm.getTables(null, null, "PAYMENT", new String[] {"TABLE"});
        if (!tables.next()) {
            query = "create table Payment (paymentID integer not null, name varchar(50) not null, price double not null, author varchar(50) not null, isbn varchar(13), sellerID integer not null, buyerID integer not null, cardNum varchar(16), cardSecuCode varchar(3), cardExp varchar(4), accNum varchar(12), routingNum varchar(9), method boolean, primary key (paymentID), foreign key (sellerID) references USERTABLE(userID), foreign key (buyerID) references USERTABLE(userID))";
            statement.executeUpdate(query);
        }
        
        // if "Report" is not there, create it and insert record
        tables = dbm.getTables(null, null, "REPORT", new String[] {"TABLE"});
        if (!tables.next()) {
            query = "create table Report (reportID integer not null, reporteeID integer not null, type varchar(5) not null, status boolean not null, description varchar(500) not null, comment varchar(500) not null, primary key (reportID))";
            statement.executeUpdate(query);
            query = "INSERT into Report values (1, 0, 'OTHER', false, 'not completed function for selling', '')";
            statement.executeUpdate(query);
        }
        
    }
    
    public static int maxUserID() throws SQLException {
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT MAX(userID) AS maxUserID FROM UserTable";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt("maxUserID");
    }
    
    public static int maxReportID() throws SQLException {
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT MAX(reportID) AS maxReportID FROM Report";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt("maxReportID");
    }
    
    public static int maxTextbookID() throws SQLException {
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT MAX(textbookID) AS maxTextbookID FROM Textbook";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt("maxTextbookID");
    }
    
    public static ArrayList<Report> resolved() throws SQLException {
        Connection connection = CIS454Project.makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM Report WHERE status = true";
        ResultSet resultSet = statement.executeQuery(query);
        
        ArrayList<Report> alist = new ArrayList<>();
        while (resultSet.next()) {
            alist.add(new Report(resultSet.getInt("reportID"), resultSet.getInt("reporteeID"), resultSet.getString("type"), resultSet.getString("description"), resultSet.getBoolean("status"), resultSet.getString("comment")));
        }
        
        return alist;
    }
    
    public static ArrayList<Report> unresolved() throws SQLException {
        Connection connection = CIS454Project.makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM Report WHERE status = false";
        ResultSet resultSet = statement.executeQuery(query);
        
        ArrayList<Report> alist = new ArrayList<>();
        while (resultSet.next()) {
            alist.add(new Report(resultSet.getInt("reportID"), resultSet.getInt("reporteeID"), resultSet.getString("type"), resultSet.getString("description"), resultSet.getBoolean("status"), resultSet.getString("comment")));
        }
        
        return alist;
    }
    
    public static ArrayList<Textbook> getTextbook(String search) throws SQLException {
        Connection connection = CIS454Project.makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM Textbook WHERE name = '"+search+"' OR author = '"+search+"' OR isbn = '"+search+"'";
        ResultSet resultSet = statement.executeQuery(query);
        
        ArrayList<Textbook> alist = new ArrayList<>();
        while (resultSet.next()) {
            alist.add(new Textbook(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getString("author"), resultSet.getString("isbn"), resultSet.getInt("textbookID"), resultSet.getInt("seller")));
        }
        
        return alist;
    }
    
    public static void main(String[] args) throws SQLException {
        createTable();
        launch(args);
    }
    
}
