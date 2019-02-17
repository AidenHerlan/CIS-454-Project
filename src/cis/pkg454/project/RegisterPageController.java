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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class RegisterPageController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField emailField;
    @FXML
    private ImageView backArrow;
    @FXML
    private Label usernameErr;
    @FXML
    private Label emailErr;

    @FXML
    void registerUser(ActionEvent event) throws Exception {
        // Validate that all fields have input of the correct format
        // Check if username is already in database
        
        // If input is invalid, show an error message
        if (!emailErr.getText().equals("") || 
                !usernameErr.getText().equals("") ||
                usernameField.getCharacters().length() == 0 ||
                usernameField.getCharacters().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please input valid information in all fields");
            alert.showAndWait();
        }

        // On successful registraction, change scene to main page
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        
        window.setScene(mainPageScene);
        window.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up validation hints for each field
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // If the input is invalid, create and add label notifying user
            if (!emailField.getCharacters().toString().matches("^[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+.[a-zA-Z0-9_.]+")) { 
                emailErr.setText("* Provide a valid email address");
                emailErr.setWrapText(true);
            }
            // Else, remove any existing label from the grid
            else {
                emailErr.setText("");
            }
        });
    }    

    @FXML
    private void toLoginPage(MouseEvent event) throws Exception {
        // Load login info page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent loginPageParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene loginPageScene = new Scene(loginPageParent);
        
        window.setScene(loginPageScene);
        window.show();
    }
    
}
