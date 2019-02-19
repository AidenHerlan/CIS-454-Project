/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author OliviaFlynn
 */
public class LoginPageController implements Initializable {
    
    @FXML private TextField usernameField;
    @FXML private Button loginButton;
    @FXML private PasswordField passwordField;
    @FXML private Label registerLink;
    @FXML
    private AnchorPane rootPane;

    @FXML
    void login(ActionEvent event) throws Exception{
        String username = usernameField.getCharacters().toString();
        String password = passwordField.getCharacters().toString();
        
        // verification
        Connection connection = CIS454Project.makeConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT name, balance, isAdmin, email, phoneNumber, address, id FROM UserTable WHERE username = '"+username+"' AND password = '"+password+"'";
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Wrong username/password");
            alert.setHeaderText(null);
            alert.setContentText("Your username/password is wrong!");
            alert.showAndWait();
            return;
        }
        
        // get user info from backend
        String name = resultSet.getString("name");
        double balance = resultSet.getDouble("balance");
        boolean isAdmin = resultSet.getBoolean("isAdmin");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phoneNumber");
        String address = resultSet.getString("address");
        int id = resultSet.getInt("id");
        
        // update the user object
        CIS454Project.currentUser.setName(name);
        CIS454Project.currentUser.setBalance(balance);
        CIS454Project.currentUser.setIsAdmin(isAdmin);
        CIS454Project.currentUser.setUsername(username);
        CIS454Project.currentUser.setPassword(password);
        CIS454Project.currentUser.setEmail(email);
        CIS454Project.currentUser.setPhoneNumber(phoneNumber);
        CIS454Project.currentUser.setAddress(address);
        CIS454Project.currentUser.setId(id);
        
        // On verification, change scene to main page
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        
        window.setScene(mainPageScene);
        window.show();
    }

    @FXML
    void toRegisterPage(Event event) throws Exception {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent registerParent = FXMLLoader.load(getClass().getResource("RegisterPage.fxml"));
        Scene registerScene = new Scene(registerParent);
        
        window.setScene(registerScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
