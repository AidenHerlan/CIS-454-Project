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
    }    

    @FXML
    private void toMainPage(MouseEvent event) throws Exception {
        // Load account info page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent accountInfoPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene accountInfoPageScene = new Scene(accountInfoPageParent);
        
        window.setScene(accountInfoPageScene);
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
        
        // Create Label objects
        Label usernameLabel = new Label("Username: ");
        Label nameLabel = new Label("Name: ");
        Label passwordLabel = new Label("Password: ");
        Label phoneNumberLabel = new Label("Phone Number: ");
        Label emailLabel = new Label("Email: ");
        Label locationLabel = new Label("Address: ");
        
        // Create TextField objects
        TextField usernameField = new TextField();
        TextField nameField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField phoneNumberField = new TextField();
        TextField emailField = new TextField();
        TextField locationField = new TextField();

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
        
        // Add grid to dialog pane
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("Done", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        // Fill in once User class is available
//        dialog.setResultConverter(new Callback<ButtonType, >() {
//            @Override
//            public PhoneBook call(ButtonType b) {
//
//                if (b == buttonTypeOk) {
//
//                    return new PhoneBook(text1.getText(), text2.getText());
//                }
//
//                return null;
//            }
//        });

        Optional<?> result = dialog.showAndWait();
    }

    /**
     * Confirms deletion by constructing an alert
     * Calls server to delete account if OK is clicked
     * Does nothing if Cancel is clicked
     * @param event 
     */
    @FXML
    private void deleteAccountPrompt(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Account");
        String s = "You are about to delete your account. Are you sure you want to do that?";
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            // call to server to delete
        }
    }
    
}
