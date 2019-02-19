/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
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
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        Statement stmt;
        String query = "create table UserTable (id integer not null, name varchar(30), username varchar(30) not null, password varchar(30) not null, address varchar(50), balance double default 0.0, isAdmin boolean default false, email varchar(30) not null, phoneNumber varchar(10), primary key (id))";
        Driver derbyEmbeddedDriver = new EmbeddedDriver();
        DriverManager.registerDriver(derbyEmbeddedDriver);
        Connection connection = DriverManager.getConnection("jdbc:derby:CIS454Database;create=true");
        stmt = connection.createStatement();
        DatabaseMetaData dbm = connection.getMetaData();
        // check if "UserTable" table is there
        ResultSet tables = dbm.getTables(null, null, "USERTABLE", new String[] {"TABLE"});
        if (tables.next()) stmt.execute("drop table USERTABLE");
        stmt.executeUpdate(query);
        query = "INSERT into UserTable values (1, 'Default', 'default', 'default', '', 0.0, false, 'default@gmail.com', '3151234567')";
        stmt.executeUpdate(query);
                
        launch(args);
    }
    
}
