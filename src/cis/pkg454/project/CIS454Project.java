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
        Statement statement;
        String query = "create table UserTable (id integer not null, name varchar(30), username varchar(30) not null, password varchar(30) not null, address varchar(50), balance double default 0.0, isAdmin boolean default false, email varchar(30) not null, phoneNumber varchar(10), primary key (id))";
        Connection connection = makeConnection();
        statement = connection.createStatement();
        DatabaseMetaData dbm = connection.getMetaData();
        // check if "UserTable" table is there
        ResultSet tables = dbm.getTables(null, null, "USERTABLE", new String[] {"TABLE"});
        if (tables.next()) statement.execute("drop table USERTABLE");
        statement.executeUpdate(query);
        query = "INSERT into UserTable values (1, 'Default', 'default', 'default', '', 0.0, true, 'default@gmail.com', '3151234567')";
        statement.executeUpdate(query);
        query = "INSERT into UserTable values (2, 'Dummy', 'dummy', 'dummy', '', 0.0, false, 'dummy@gmail.com', '3159875643')";
        statement.executeUpdate(query);
    }
    
    public static int maxID() throws SQLException {
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT MAX(id) AS maxID FROM UserTable";
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
