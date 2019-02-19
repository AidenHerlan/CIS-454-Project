/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
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
    private Label totalPriceText;
    @FXML
    private TextField nameField;
    @FXML
    private TextField shippingAddressField;
    @FXML
    private Button continueButton;
    @FXML
    private ToggleGroup paymentMethod;
    @FXML
    private RadioButton checkRB;
    @FXML
    private RadioButton cardRB;
    
    int totalCost = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set the pay by check radio button to be seleted by default
        checkRB.setSelected(true);
        
        // Calculate the total cost
        for (int i = 0; i < CIS454Project.currentUser.getShoppingCart().size(); i++) {
            totalCost += CIS454Project.currentUser.getShoppingCart().get(i).getPrice();
        }
        
        // Provide total cost to the appropriate label
        totalPriceText.setText("Total Price: $" + totalCost);
    }    

    @FXML
    private void toShoppingCartPage(MouseEvent event) throws Exception {
        // Load shopping cart page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent shoppingCartPageParent = FXMLLoader.load(getClass().getResource("ShoppingCartPage.fxml"));
        Scene shoppingCartPageScene = new Scene(shoppingCartPageParent);
        
        window.setScene(shoppingCartPageScene);
        window.show();
    }

    /**
     * Creates and displays the correct prompt for credit or check payment, then  validates and processes the information
     * @param event 
     */
    @FXML
    private void purchasePrompt(ActionEvent event) {
        // Show an error message if name or shipping address fields are empty
        if (nameField.getCharacters().length() == 0 || shippingAddressField.getCharacters().length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please provide information for Recipient Name and Shipping Address");
            alert.showAndWait();
            return;
        }
        // Show an error message if they do not have enough money to buy all the books
        if (CIS454Project.currentUser.getBalance() < totalCost) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Insufficient Funds");
            alert.setContentText("There is not enough money in your account to make this purchase. Please remove some items from your shopping cart and try again, or get your money up");
            alert.showAndWait();
            return;
        }
        
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
                if(!routingNumberField.getCharacters().toString().matches("[0-9]+") || 
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
                // If there are input errors, show an error message
                if (!accountNumberErr.getText().equals("") || !routingNumberErr.getText().equals("")) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setContentText("Please input valid information, specified next to each invalid field");
                    alert.showAndWait();
                    
                    purchasePrompt(new ActionEvent());
                }
                else {
                    // Create payment obj for each book in shopping cart and send to backend 
                    ArrayList<Payment> listOfPayments = new ArrayList<Payment>();
                    
                    ArrayList<Textbook> shoppingCart = CIS454Project.currentUser.getShoppingCart();
                    for(int i = 1; i <= shoppingCart.size(); i++) {
                        // Create Payment object
                        Payment userPayment = new Payment(
                                shoppingCart.get(i).getId(), 
                                shoppingCart.get(i).getSellerID(), 
                                CIS454Project.currentUser.getId(),
                                accountNumberField.getText(),
                                routingNumberField.getText(),
                                shoppingCart.get(i).getPrice());
                        
                        // Add to list of payments
                        listOfPayments.add(userPayment);
                    }
                    
                    // Send the list of payments to update the back end
                    CIS454Project.submitPayments(listOfPayments, totalCost);
                }
            }
        }
        // if they are paying by card, collect card number, cvv code, and expiration
        else if ((RadioButton)paymentMethod.getSelectedToggle() == cardRB) {
            // Create Dialog window
            Dialog<?> dialog = new Dialog<>();
            dialog.setTitle("Pay by credit card");
            dialog.setHeaderText("Provide credit card information for payment processing, then press Done");
            dialog.setResizable(false);
            
            // Create grid object
            GridPane grid = new GridPane();
            grid.setVgap(5.0);

            // Create and style Label objects
            Label cardNumberLabel = new Label("Credit Card Number: ");
            Label cvvNumberLabel = new Label("CVV: ");
            Label expirationLabel = new Label("Expiration Date: ");
            Label cardNumberErr = new Label("* Must 19 numbers");
            Label cvvNumberErr = new Label("* Must be 3 numbers");
            Label expirationErr = new Label("* Must be of the form mm/yy");
            
            cardNumberErr.setFont(Font.font("System", 10));
            cardNumberErr.setTextFill(Paint.valueOf("ORANGE"));
            cvvNumberErr.setFont(Font.font("System", 10));
            cvvNumberErr.setTextFill(Paint.valueOf("ORANGE"));
            expirationErr.setFont(Font.font("System", 10));
            expirationErr.setTextFill(Paint.valueOf("ORANGE"));

            // Create TextField objects
            TextField cardNumberField = new TextField();
            TextField cvvNumberField = new TextField();
            TextField expirationField = new TextField();
            
            // Set up validation hints for each field
            cardNumberField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                // If the input is invalid, create and add label notifying user
                if (!cardNumberField.getCharacters().toString().matches("[0-9]+") || 
                        cardNumberField.getCharacters().length() < 1 || 
                        cardNumberField.getCharacters().length() > 17) { 
                    cardNumberErr.setText("* Must be 19 numbers");
                    cardNumberErr.setWrapText(true);
                }
                // Else, remove any existing label from the grid
                else {
                    cardNumberErr.setText("");
                }
            });
            cvvNumberField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                // If the input is invalid, create and add label notifying user
                if(!cvvNumberField.getCharacters().toString().matches("[0-9]+") || 
                        cvvNumberField.getCharacters().length() != 9) { 
                    cvvNumberErr.setText("* Must be 3 numbers");
                    cvvNumberErr.setWrapText(true);
                }
                // Else, remove any existing label from the grid
                else {
                    cvvNumberErr.setText("");
                }
            });
            expirationField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                // If the input is invalid, create and add label notifying user
                if(!expirationField.getCharacters().toString().matches("^(0[1-9]|1[0-2])\\/?([0-9]{2})$")) { 
                    expirationErr.setText("* Must be of the form mm/yy");
                    expirationErr.setWrapText(true);
                }
                // Else, remove any existing label from the grid
                else {
                    expirationErr.setText("");
                }
            });

            // Add elements to grid
            grid.add(cardNumberLabel, 1, 1);
            grid.add(cvvNumberLabel, 1, 2);
            grid.add(expirationLabel, 1, 3);
            grid.add(cardNumberField, 2, 1);
            grid.add(cvvNumberField, 2, 2);
            grid.add(expirationField, 2, 3);
            grid.add(cardNumberErr, 3, 1);
            grid.add(cvvNumberErr, 3, 2);
            grid.add(expirationErr, 3, 3);

            // Add grid to dialog pane
            dialog.getDialogPane().setContent(grid);

            ButtonType buttonTypeOk = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

            Optional<?> result = dialog.showAndWait();
            
            // Validate fields when Done button is pressed
            if (dialog.getResult() == buttonTypeOk) {
                // If there are input errors, show 
                if (!cardNumberErr.getText().equals("") || 
                        !cvvNumberErr.getText().equals("") ||
                        !expirationErr.getText().equals("")) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setContentText("Please input valid information, specified next to each invalid field");
                    alert.showAndWait();
                    
                    purchasePrompt(new ActionEvent());
                }
                else {
                    // Create payment obj for each book in shopping cart and send to backend 
                    ArrayList<Payment> listOfPayments = new ArrayList<Payment>();
                    
                    ArrayList<Textbook> shoppingCart = CIS454Project.currentUser.getShoppingCart();
                    for(int i = 1; i <= shoppingCart.size(); i++) {
                        // Create Payment object
                        Payment userPayment = new Payment(
                                shoppingCart.get(i).getId(), 
                                shoppingCart.get(i).getSellerID(), 
                                CIS454Project.currentUser.getId(),
                                cardNumberField.getText(),
                                cvvNumberField.getText(),
                                expirationField.getText(),
                                shoppingCart.get(i).getPrice());
                        
                        // Add to list of payments
                        listOfPayments.add(userPayment);
                    }
                    
                    // Send the list of payments to update the back end
                    CIS454Project.submitPayments(listOfPayments, totalCost);
                }
            }

        }

    }
}
