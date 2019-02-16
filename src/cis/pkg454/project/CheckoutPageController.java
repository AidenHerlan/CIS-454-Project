/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.awt.Color;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class CheckoutPageController implements Initializable {

    @FXML
    private ImageView backArrow;
    @FXML
    private Label titleText;
    @FXML
    private Label subtotalText;
    @FXML
    private Label totalPriceText;
    @FXML
    private TextField nameField;
    @FXML
    private Label shippingAddressField;
    @FXML
    private Button continueButton;
    @FXML
    private ToggleGroup paymentMethod;
    @FXML
    private RadioButton checkRB;
    @FXML
    private RadioButton cardRB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set the pay by check radio button to be seleted by default
        checkRB.setSelected(true);
    }    

    @FXML
    private void toShoppingCartPage(MouseEvent event) throws Exception {
        // Load shopping cart page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent accountInfoPageParent = FXMLLoader.load(getClass().getResource("ShoppingCartPage.fxml"));
        Scene accountInfoPageScene = new Scene(accountInfoPageParent);
        
        window.setScene(accountInfoPageScene);
        window.show();
    }

    /**
     * Displays the correct prompt for credit or check payment, then  validates and processes the information
     * @param event 
     */
    @FXML
    private void purchasePrompt(ActionEvent event) {
        // Variable to mark whether input is valid or not
        boolean valid = false;
        
        // if they are paying by check, collect account number and routing number
        if ((RadioButton)paymentMethod.getSelectedToggle() == checkRB) {
            // Create Dialog window
            Dialog<?> dialog = new Dialog<>();
            dialog.setTitle("Pay by check");
            dialog.setHeaderText("Provide bank account and routing numbers for check processing, then press Done");
            dialog.setResizable(false);
            
            // Create grid object
            GridPane grid = new GridPane();
            grid.setVgap(5.0);

            // Create and style Label objects
            Label accountNumberLabel = new Label("Bank Account Number: ");
            Label routingNumberLabel = new Label("Routing Number: ");
            Label accountNumberErr = new Label("* Must be between 1 and 17 numbers");
            Label routingNumberErr = new Label("* Must be 9 numbers");
            
            accountNumberErr.setFont(Font.font("System", 10));
            accountNumberErr.setTextFill(Paint.valueOf("ORANGE"));
            routingNumberErr.setFont(Font.font("System", 10));
            routingNumberErr.setTextFill(Paint.valueOf("ORANGE"));

            // Create TextField objects
            TextField accountNumberField = new TextField();
            TextField routingNumberField = new TextField();
            
            // Set up validation hints for each field
            accountNumberField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                // If the input is invalid, create and add label notifying user
                if (!accountNumberField.getCharacters().toString().matches("[0-9]+") || 
                        accountNumberField.getCharacters().length() < 1 || 
                        accountNumberField.getCharacters().length() > 17) { 
                    accountNumberErr.setText("* Must be between 1 and 17 numbers");
                    accountNumberErr.setWrapText(true);
                }
                // Else, remove any existing label from the grid
                else {
                    accountNumberErr.setText("");
                }
            });
            routingNumberField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                // If the input is invalid, create and add label notifying user
                if(!accountNumberField.getCharacters().toString().matches("[0-9]+") || 
                        routingNumberField.getCharacters().length() != 9) { 
                    routingNumberErr.setText("* Must be 9 numbers");
                    routingNumberErr.setWrapText(true);
                }
                // Else, remove any existing label from the grid
                else {
                    routingNumberErr.setText("");
                }
            });

            // Add elements to grid
            grid.add(accountNumberLabel, 1, 1);
            grid.add(routingNumberLabel, 1, 2);
            grid.add(accountNumberField, 2, 1);
            grid.add(routingNumberField, 2, 2);
            grid.add(accountNumberErr, 3, 1);
            grid.add(routingNumberErr, 3, 2);

            // Add grid to dialog pane
            dialog.getDialogPane().setContent(grid);

            ButtonType buttonTypeOk = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

            Optional<?> result = dialog.showAndWait();
            // Validate fields when Done button is pressed
            if (dialog.getResult() == buttonTypeOk) {
                // If there are input errors, show 
                if (!accountNumberErr.getText().equals("") || !routingNumberErr.getText().equals("")) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setContentText("Please input valid information, specified next to each invalid field");
                    alert.showAndWait();
                    
                    purchasePrompt(new ActionEvent());
                }
            }

//            Optional<?> result = dialog.showAndWait();
        }
        // if they are paying by card, collect card number, cvv code, and expiration
        else if ((RadioButton)paymentMethod.getSelectedToggle() == checkRB) {
            
        }
        
    }
    
}
