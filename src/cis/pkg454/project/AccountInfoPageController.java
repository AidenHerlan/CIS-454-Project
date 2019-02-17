/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class AccountInfoPageController implements Initializable {

    @FXML
    private ImageView backArrow;
    @FXML
    private Label titleText;
    @FXML
    private Label usernameText;
    @FXML
    private Label nameText;
    @FXML
    private Label emailText;
    @FXML
    private Label addressText;
    @FXML
    private Label phoneNumberText;
    @FXML
    private Label idText;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteAccountButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // get user object, fill labels
        usernameText.setText("Username: " + CIS454Project.currentUser.getUsername());
        nameText.setText("Name: " + CIS454Project.currentUser.getName());
        emailText.setText("Email: " + CIS454Project.currentUser.getEmail());
        addressText.setText("Address: " + CIS454Project.currentUser.getAddress());
        phoneNumberText.setText("Phone Number: " + CIS454Project.currentUser.getPhoneNumber());
        idText.setText("Account ID: " + CIS454Project.currentUser.getId());
    }    

    @FXML
    private void toMainPage(MouseEvent event) throws Exception {
        // Load main info page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent MainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene MainPageScene = new Scene(MainPageParent);
        
        window.setScene(MainPageScene);
        window.show();
    }

    /**
     * Constructs prompt for user to edit account info
     * @param event 
     */
    @FXML
    private void editInfoPrompt(ActionEvent event) {
        // Create Dialog window
        Dialog<?> dialog = new Dialog<>();
        dialog.setTitle("Edit Account Info");
        dialog.setHeaderText("Edit information and press Done or exit out to discard changes");
        dialog.setResizable(false);
        
        // Create and style Label objects
        Label usernameLabel = new Label("Username: ");
        Label nameLabel = new Label("Name: ");
        Label passwordLabel = new Label("New Password: ");
        Label phoneNumberLabel = new Label("Phone Number: ");
        Label emailLabel = new Label("Email: ");
        Label locationLabel = new Label("Address: ");
        Label usernameErr = new Label("");
        Label emailErr = new Label("");
        
        usernameErr.setFont(Font.font("System", 10));
        usernameErr.setTextFill(Paint.valueOf("ORANGE"));
        emailErr.setFont(Font.font("System", 10));
        emailErr.setTextFill(Paint.valueOf("ORANGE"));
        
        // Create TextField objects
        TextField usernameField = new TextField(CIS454Project.currentUser.getUsername());
        TextField nameField = new TextField(CIS454Project.currentUser.getName());
        PasswordField passwordField = new PasswordField();
        TextField phoneNumberField = new TextField(CIS454Project.currentUser.getPhoneNumber());
        TextField emailField = new TextField(CIS454Project.currentUser.getEmail());
        TextField locationField = new TextField(CIS454Project.currentUser.getAddress());
        
        // Set up validation hints for each field
        usernameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // If the input is invalid, create and add label notifying user
            if (usernameField.getCharacters().length() > 0) {  //replace later with username validation
                usernameErr.setText("* This username is already taken");
                usernameErr.setWrapText(true);
            }
            // Else, remove any existing label from the grid
            else {
                usernameErr.setText("");
            }
        });
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // If the input is invalid, create and add label notifying user
            if (!emailField.getCharacters().toString().matches("^[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+.[a-zA-Z0-9_.]+")) {
                emailErr.setText("* Please provide a valid email");
                emailErr.setWrapText(true);
            }
            // Else, remove any existing label from the grid
            else {
                emailErr.setText("");
            }
        });

        // Create gridpane and add elements
        GridPane grid = new GridPane();
        grid.setVgap(5.0);
        
        grid.add(usernameLabel, 1, 1);
        grid.add(nameLabel, 1, 2);
        grid.add(passwordLabel, 1, 3);
        grid.add(phoneNumberLabel, 1, 4);
        grid.add(emailLabel, 1, 5);
        grid.add(locationLabel, 1, 6);
        
        grid.add(usernameField, 2, 1);
        grid.add(nameField, 2, 2);
        grid.add(passwordField, 2, 3);
        grid.add(phoneNumberField, 2, 4);
        grid.add(emailField, 2, 5);
        grid.add(locationField, 2, 6);
        
        grid.add(usernameErr, 3, 1);
        grid.add(emailErr, 3, 5);
        
        // Add grid to dialog pane
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("Done", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

//      Fill in once User class is available, create User object to update database INCLUDE VALIDATION
        Optional<?> result = dialog.showAndWait();

        // Validate fields when Done button is pressed
        if (dialog.getResult() == buttonTypeOk) {
            // If there are input errors, show 
            if (!usernameErr.getText().equals("") || 
                    !emailErr.getText().equals("") ||
                    usernameField.getText().length() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setContentText("Please input valid information, specified next to each invalid field. Make sure to provide an email, username, and password.");
                alert.showAndWait();

                editInfoPrompt(new ActionEvent());
            }
            // Otherwise, create a new user object with updated information. Use it to 
            // update the app state
            else {
                User updatedUser = new User(
                        CIS454Project.currentUser, 
                        usernameField.getText(), 
                        emailField.getText(), 
                        passwordField.getText(),
                        nameField.getText(),
                        locationField.getText(),
                        phoneNumberField.getText());
                
                // Pass updated user object to be added to database
                CIS454Project.updateUser(updatedUser);
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Updated");
                alert.setContentText("Your information has been successfully updated. Reload the page to see changes.");
                alert.showAndWait();
            }
        }
    }

    /**
     * Confirms deletion by constructing an alert
     * Calls server to delete account if OK is clicked
     * Does nothing if Cancel is clicked
     * @param event 
     */
    @FXML
    private void deleteAccountPrompt(ActionEvent event) throws Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Account");
        String s = "You are about to delete your account. Are you sure you want to do that?";
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            // call to server to delete
            
            // Navigate back to login page
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Parent loginPageParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene loginPageScene = new Scene(loginPageParent);

            window.setScene(loginPageScene);
            window.show();
        }
    }
    
}
