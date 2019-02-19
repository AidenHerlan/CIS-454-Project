/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

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
    public static User currentUser = new User("", "", "", 1.0, false);
    
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
     * @return 
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
        tables = dbm.getTables(null, null, "TEXTBOOK", new String[] {"TABLE"});
        if (tables.next()) statement.execute("drop table TEXTBOOK");
        // create Textbook table
        query = "create table Textbook (textbookID integer not null, name varchar(50) not null, price double not null, author varchar(50), isbn varchar(13), seller integer not null, primary key (textbookID), foreign key (seller) references USERS(userID))";
        statement.executeUpdate(query);
        
        // check if "Report" table is there and drop it
        tables = dbm.getTables(null, null, "REPORT", new String[] {"TABLE"});
        if (tables.next()) statement.execute("drop table REPORT");
        // create ""Report" table
        query = "create table Report (reportID integer not null, userID integer not null, type varchar(25), status varchar(25), description varchar(500), comment varchar(500), primary key (reportID), foreign key (userID) REFERENCES USERS(userID))";
        statement.executeUpdate(query);
        
        // check if "Payment" tale is there and drop it
        tables = dbm.getTables(null, null, "PAYMENT", new String[] {"TABLE"});
        if (tables.next()) statement.execute("drop table PAYMENT");
        // create "Payment" table
        query = "create table Payment (paymentID integer NOT NULL, sellerID integer NOT NULL, buyerID integer, cardNum varchar(16), cardSecuCode varchar(3), cardExp varchar(4), accNum varchar(12), routingNum varchar(9), method boolean, primary key (paymentID), foreign key (sellerID) references USERS(userID), foreign key (buyerID) references USERS(userID))";
        statement.executeUpdate(query);
        
    }
    
    public static int maxID() throws SQLException {
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT MAX(userID) AS maxID FROM UserTable";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt("maxID");
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
