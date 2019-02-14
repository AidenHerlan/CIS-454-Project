/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class RegisterPageController implements Initializable {

    @FXML
    private TextArea usernameField;

    @FXML
    private Button registerButton;


    @FXML
    private TextArea emailField;
    @FXML
    private Label toLoginPageButton;

    @FXML
    void registerUser(ActionEvent event) throws Exception {

        // On registraction, change scene to main page
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        
        window.setScene(mainPageScene);
        window.show();
    }
    
    @FXML
    private void toLoginPage(MouseEvent event) throws Exception {
        // On registraction, change scene to main page
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent loginPageParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene loginPageScene = new Scene(loginPageParent);
        
        window.setScene(loginPageScene);
        window.show();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
