/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


/**
 *
 * @author OliviaFlynn
 */
public class LoginPageController implements Initializable {
    
    @FXML private TextArea usernameField;
    @FXML private Button loginButton;
    @FXML private TextArea passwordField;
    @FXML private Label registerLink;

    @FXML
    void login(ActionEvent event) throws Exception{
        
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
